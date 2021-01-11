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
import com.pemsel.aoefaa.soccerdb.adapter.TeamAdapter;
import com.pemsel.aoefaa.soccerdb.data.Favorite;
import com.pemsel.aoefaa.soccerdb.data.Team;
import com.pemsel.aoefaa.soccerdb.db.FavoriteDbHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment{

    private RecyclerView recyclerView;
    FavoriteDbHelper favoriteDbHelper;
    private ArrayList<Favorite> favoriteList;
    private FavoriteAdapter favoriteAdapter;

    public FavoritesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Favorites");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvFav);
        favoriteAdapter = new FavoriteAdapter(getActivity());
        favoriteDbHelper = new FavoriteDbHelper(getContext());

        favoriteList = favoriteDbHelper.allFavorite();
        favoriteAdapter.setFavoriteList(favoriteList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoriteAdapter);

    }

}