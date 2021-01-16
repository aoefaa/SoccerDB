package com.pemsel.aoefaa.soccerdb.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.activity.MatchDetailActivity;
import com.pemsel.aoefaa.soccerdb.model.MatchModel;
import com.pemsel.aoefaa.soccerdb.model.TeamResponse;
import com.pemsel.aoefaa.soccerdb.network.ApiClient;
import com.pemsel.aoefaa.soccerdb.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private TeamAdapter teamAdapter;
    private List<MatchModel> list = new ArrayList<>();

    public MatchAdapter() {
    }

    public void setMatches (List<MatchModel> matchModels){
        this.list.clear();
        this.list = matchModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MatchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_matches, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MatchDetailActivity.class);
            intent.putExtra("detail", list.get(position));
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivHomeTeam, ivAwayTeam;
        TextView tvHomeTeam, tvAwayteam;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Text View
            tvHomeTeam = itemView.findViewById(R.id.tv_home_team);
            tvAwayteam = itemView.findViewById(R.id.tv_away_team);
            //Image View
            ivHomeTeam = itemView.findViewById(R.id.iv_home_team);
            ivAwayTeam = itemView.findViewById(R.id.iv_away_team);
        }

        public void bind(MatchModel matchModel) {

        }
    }
}