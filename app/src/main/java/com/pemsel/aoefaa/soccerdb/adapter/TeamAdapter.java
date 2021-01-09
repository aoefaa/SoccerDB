package com.pemsel.aoefaa.soccerdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.activity.DetailActivity;
import com.pemsel.aoefaa.soccerdb.data.Team;
import com.pemsel.aoefaa.soccerdb.db.FavoriteDbHelper;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private Team team;
    private Context context;
    private FavoriteDbHelper favoriteDbHelper;

    private List<Team> teams = new ArrayList<>();

    public void setData (List<Team> teams){
        this.context = context;
        this.teams.clear();
        this.teams = teams;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_teams, parent, false);
        return new ViewHolder(view);
    }

    private void createTableOnFirstStart() {

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, int position) {
        holder.bind(teams.get(position));
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("detail", teams.get(position));
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgTeam;
        private TextView tvNama, tvDesc, tvTahun;
        Button btFav;

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
