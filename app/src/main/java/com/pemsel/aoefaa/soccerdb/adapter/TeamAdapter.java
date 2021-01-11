package com.pemsel.aoefaa.soccerdb.adapter;

import android.content.Context;
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
import com.pemsel.aoefaa.soccerdb.activity.DetailActivity;
import com.pemsel.aoefaa.soccerdb.data.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private List<Team> items = new ArrayList<>();

    public interface onSelectData {
        void onSelected(Team team);
    }

    public void setData (List<Team> teams){
        this.items.clear();
        this.items = teams;
        notifyDataSetChanged();
    }

    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_teams, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position));
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("detail", items.get(position));
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgTeam;
        private TextView tvNama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTeam = itemView.findViewById(R.id.imgTeam);
            tvNama = itemView.findViewById(R.id.tvNama);

        }

        public void bind(Team team) {
            tvNama.setText(team.getStrTeam());
            Glide.with(itemView.getContext())
                    .load(team.getStrTeamBadge())
                    .apply(new RequestOptions())
                    .into(imgTeam);
        }
    }

}
