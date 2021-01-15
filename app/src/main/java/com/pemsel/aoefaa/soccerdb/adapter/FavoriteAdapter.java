package com.pemsel.aoefaa.soccerdb.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.activity.DetailFavoriteActivity;
import com.pemsel.aoefaa.soccerdb.model.FavoriteModel;
import com.pemsel.aoefaa.soccerdb.model.TeamModel;
import com.pemsel.aoefaa.soccerdb.db.DBHelper;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private ArrayList<FavoriteModel> favoriteModelList = new ArrayList<>();
    private Activity activity;
    private DBHelper DBHelper;
    private ArrayList<TeamModel> teamModel;

    public FavoriteAdapter(Activity activity) {
        this.activity = activity;
        DBHelper = new DBHelper(activity);
    }

    public ArrayList<FavoriteModel> getFavoriteModelList() {
        return favoriteModelList;
    }

    public void setFavoriteModelList(ArrayList<FavoriteModel> list) {
        if (list.size() > 0) {
            this.favoriteModelList.clear();
            this.favoriteModelList = favoriteModelList;
        }

        this.favoriteModelList.addAll(list);
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
        holder.bind(favoriteModelList.get(position));
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailFavoriteActivity.class);
            intent.putExtra("detail", favoriteModelList.get(position));
            view.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return favoriteModelList.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {

        TextView tvFav;
        ImageView ivFav;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFav = itemView.findViewById(R.id.tvFav);
            ivFav = itemView.findViewById(R.id.ivFav);

        }

        public void bind(FavoriteModel favoriteModelList) {
            tvFav.setText(favoriteModelList.getTeam_name());
            Glide.with(itemView.getContext())
                    .load(favoriteModelList.getTeam_badge())
                    .apply(new RequestOptions())
                    .into(ivFav);
        }
    }

}
