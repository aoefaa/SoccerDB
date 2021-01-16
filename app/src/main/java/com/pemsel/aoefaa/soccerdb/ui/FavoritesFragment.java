package com.pemsel.aoefaa.soccerdb.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.adapter.FavoriteAdapter;
import com.pemsel.aoefaa.soccerdb.model.FavoriteModel;
import com.pemsel.aoefaa.soccerdb.db.DBHelper;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment{

    private RecyclerView recyclerView;
    DBHelper DBHelper;
    private ArrayList<FavoriteModel> favoriteModelList;
    private FavoriteAdapter favoriteAdapter;

    public FavoritesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_fav);
        favoriteAdapter = new FavoriteAdapter(getActivity());
        DBHelper = new DBHelper(getContext());

        favoriteModelList = DBHelper.getFavorite();
        favoriteAdapter.setFavoriteModelList(favoriteModelList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoriteAdapter);

    }

}