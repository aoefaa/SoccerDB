package com.pemsel.aoefaa.soccerdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.data.Favorite;
import com.pemsel.aoefaa.soccerdb.db.FavoriteDbHelper;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Context context;
    private List<Favorite> favoriteList;
    private FavoriteDbHelper favoriteDbHelper;

    public FavoriteAdapter(Context context, List<Favorite> favoriteList) {
        this.context = context;
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorites,
                parent, false);
        favoriteDbHelper = new FavoriteDbHelper(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvFav.setText(favoriteList.get(position).getTeam_name());
        holder.ivFav.setImageResource(favoriteList.get(position).getTeam_badge());
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFav;
        ImageView ivFav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFav = itemView.findViewById(R.id.tvFav);
            ivFav = itemView.findViewById(R.id.ivFav);

        }
    }
}
