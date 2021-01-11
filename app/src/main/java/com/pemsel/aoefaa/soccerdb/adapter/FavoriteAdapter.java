package com.pemsel.aoefaa.soccerdb.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.activity.DetailActivity;
import com.pemsel.aoefaa.soccerdb.data.Favorite;
import com.pemsel.aoefaa.soccerdb.data.Team;
import com.pemsel.aoefaa.soccerdb.db.FavoriteDbHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private ArrayList<Favorite> favoriteList = new ArrayList<>();
    private Activity activity;
    private FavoriteDbHelper favoriteDbHelper;
    private ArrayList<Team> team = new ArrayList<>();

    public FavoriteAdapter(Activity activity) {
        this.activity = activity;
        favoriteDbHelper = new FavoriteDbHelper(activity);
    }

    public ArrayList<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(ArrayList<Favorite> list) {
        if (list.size() > 0) {
            this.favoriteList.clear();
        }

        this.favoriteList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_favorites,
                parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.bind(favoriteList.get(position));
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("detail", team.get(position));
            view.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {

        TextView tvFav;
        ImageView ivFav;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFav = itemView.findViewById(R.id.tvFav);
            ivFav = itemView.findViewById(R.id.ivFav);

        }

        public void bind(Favorite favoriteList) {
            tvFav.setText(favoriteList.getTeam_name());
            Glide.with(itemView.getContext())
                    .load(favoriteList.getTeam_badge())
                    .apply(new RequestOptions())
                    .into(ivFav);
        }
    }

}
