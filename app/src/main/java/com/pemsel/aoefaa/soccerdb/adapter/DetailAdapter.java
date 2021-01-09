/*
package com.pemsel.aoefaa.soccerdb.adapter;

import android.content.Context;
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

import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.data.Team;
import com.pemsel.aoefaa.soccerdb.db.FavoriteDbHelper;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private ArrayList<Team> teams;
    private Context context;
    private FavoriteDbHelper favoriteDbHelper;

    public DetailAdapter(ArrayList<Team> teams, Context context) {
        this.teams = teams;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favoriteDbHelper = new FavoriteDbHelper(context);
        //create table on first
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detail,
                parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Team team = teams.get(position);
        
        readCursorData(team, holder);
        holder.tvNama.setText(team.getStrTeam());
        holder.tvTahun.setText(team.getIntFormedYear());
        holder.tvDeskripsi.setText(team.getIntFormedYear());
        holder.imgTeam.setImageResource(Integer.parseInt(team.getStrTeam()));
    }

    private void readCursorData(Team team, ViewHolder viewHolder) {
        Cursor cursor = favoriteDbHelper.readAllData(team.getIdTeam());
        SQLiteDatabase db = favoriteDbHelper.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(favoriteDbHelper.FAVORITE_STATUS));
                team.setFavStatus(item_fav_status);

                //check fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.btFav.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.btFav.setBackgroundResource(R.drawable.ic_baseline_shadow_24);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTeam;
        TextView tvNama, tvTahun, tvDeskripsi;
        Button btFav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgTeam = itemView.findViewById(R.id.imgTeam);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvTahun = itemView.findViewById(R.id.tvTahun);
            tvDeskripsi = itemView.findViewById(R.id.tvDeskripsi);
            btFav = itemView.findViewById(R.id.btFav);

            btFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Team team = teams.get(position);

                    if (team.getFavStatus().equals("0")) {
                        team.setFavStatus("1");
                        favoriteDbHelper.insertIntoTheDatabase(team.getIdTeam(),
                                team.getStrTeam(),
                                team.getIntFormedYear(),
                                team.getStrDescriptionEN(),
                                team.getStrTeamBadge(),
                                team.getFavStatus());
                        btFav.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                        btFav.setSelected(true);
                    } else if (team.getFavStatus().equals("1")) {
                        team.setFavStatus("0");
                        favoriteDbHelper.removeFav(team.getIdTeam());
                        btFav.setBackgroundResource(R.drawable.ic_baseline_shadow_24);
                        btFav.setSelected(false);
                    }
                }
            });
        }
    }

    private void createTableOnFirstStart() {
        favoriteDbHelper.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }
}
*/
