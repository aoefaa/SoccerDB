package com.pemsel.aoefaa.soccerdb.adapter;

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
import com.pemsel.aoefaa.soccerdb.activity.MatchDetailActivity;
import com.pemsel.aoefaa.soccerdb.model.MatchModel;

import java.util.ArrayList;
import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private List<MatchModel> matchModels = new ArrayList<>();

    public void setMatches (List<MatchModel> matchModels){
        this.matchModels.clear();
        this.matchModels = matchModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_matches, parent, false);
        return new MatchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(matchModels.get(position));
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MatchDetailActivity.class);
            intent.putExtra("detail", String.valueOf(matchModels.get(position)));
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return matchModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHomeTeam, ivAwayTeam;
        TextView tvHomeTeam, tvAwayteam;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHomeTeam = itemView.findViewById(R.id.tv_home_team);
            tvAwayteam = itemView.findViewById(R.id.tv_away_team);
        }

        public void bind(MatchModel matchModel) {
            tvHomeTeam.setText(matchModel.getStrHomeTeam());
            tvAwayteam.setText(matchModel.getStrAwayTeam());
            /*Glide.with(itemView.getContext())
                    .load(matchModel.getIdHomeTeam())
                    .apply(new RequestOptions())
                    .into(ivHomeTeam);
            Glide.with(itemView.getContext())
                    .load(matchModel.getIdAwayTeam())
                    .apply(new RequestOptions())
                    .into(ivAwayTeam);*/
        }
    }
}
