package com.pemsel.aoefaa.soccerdb.ui;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.adapter.FavoriteAdapter;
import com.pemsel.aoefaa.soccerdb.adapter.TeamAdapter;
import com.pemsel.aoefaa.soccerdb.data.Favorite;
import com.pemsel.aoefaa.soccerdb.data.Team;
import com.pemsel.aoefaa.soccerdb.data.Teams;
import com.pemsel.aoefaa.soccerdb.db.FavoriteDbHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteDbHelper favoriteDbHelper;
    private List<Favorite> favoriteList = new ArrayList<>();
    private FavoriteAdapter favoriteAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        favoriteDbHelper = new FavoriteDbHelper(getActivity());
        recyclerView = root.findViewById(R.id.rvFav);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // add item touch helper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView); // set swipe to recyclerview

        loadData();

        return root;
    }

    private void loadData() {
        if (favoriteList != null) {
            favoriteList.clear();
        }
        SQLiteDatabase db = favoriteDbHelper.getReadableDatabase();
        Cursor cursor = favoriteDbHelper.selectAllFavoriteList();
        try {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(FavoriteDbHelper.KEY_ID));
                String title = cursor.getString(cursor.getColumnIndex(FavoriteDbHelper.ITEM_TEAM_NAME));
                String year = cursor.getString(cursor.getColumnIndex(FavoriteDbHelper.ITEM_TEAM_YEAR));
                String desc = cursor.getString(cursor.getColumnIndex(FavoriteDbHelper.ITEM_TEAM_DESC));
                int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavoriteDbHelper.ITEM_TEAM_BADGE)));
                String status = cursor.getString(cursor.getColumnIndex(FavoriteDbHelper.FAVORITE_STATUS));
                Favorite favorite = new Favorite(id, title, year, desc, image, status);
                favoriteList.add(favorite);
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

        favoriteAdapter = new FavoriteAdapter(getActivity(), favoriteList);

        recyclerView.setAdapter(favoriteAdapter);
    }

    private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition(); // get position which is swipe
            final Favorite favItem = favoriteList.get(position);
            if (direction == ItemTouchHelper.LEFT) { //if swipe left
                favoriteAdapter.notifyItemRemoved(position); // item removed from recyclerview
                favoriteList.remove(position); //then remove item
                favoriteDbHelper.removeFav(favItem.getId()); // remove item from database
            }
        }
    };

}