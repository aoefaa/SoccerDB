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
import com.pemsel.aoefaa.soccerdb.activity.TeamDetailActivity;
import com.pemsel.aoefaa.soccerdb.model.TeamModel;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private List<TeamModel> teamModels = new ArrayList<>();

    public void setData (List<TeamModel> teamModels){
        this.teamModels.clear();
        this.teamModels = teamModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_teams, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, int position) {
        holder.bind(teamModels.get(position));
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), TeamDetailActivity.class);
            intent.putExtra("detail", teamModels.get(position));
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return teamModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTeam;
        TextView tvNama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTeam = itemView.findViewById(R.id.iv_team);
            tvNama = itemView.findViewById(R.id.tv_team);
        }

        public void bind(TeamModel teamModel) {
            tvNama.setText(teamModel.getStrTeam());
            Glide.with(itemView.getContext())
                    .load(teamModel.getStrTeamBadge())
                    .apply(new RequestOptions())
                    .into(imgTeam);
        }
    }

}
