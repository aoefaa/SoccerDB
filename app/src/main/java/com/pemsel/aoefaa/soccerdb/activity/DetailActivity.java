package com.pemsel.aoefaa.soccerdb.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.data.Teams;
import com.pemsel.aoefaa.soccerdb.data.Team;
import com.pemsel.aoefaa.soccerdb.db.FavoriteDbHelper;
import com.pemsel.aoefaa.soccerdb.network.ApiClient;
import com.pemsel.aoefaa.soccerdb.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailActivity extends AppCompatActivity {
    private FavoriteDbHelper favoriteDbHelper;
    private Team team = new Team();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvNama = findViewById(R.id.tvNama);
        TextView tvTahun = findViewById(R.id.tvTahun);
        TextView tvDeskripsi = findViewById(R.id.tvDeskripsi);
        ImageView imgTeam = findViewById(R.id.imgTeam);
        Button btFav = findViewById(R.id.btFav);

        team = getIntent().getParcelableExtra("detail");

        //btFav.setOnClickListener();

        /*btFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        });*/
        
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Teams> call = service.getTeamDetail(team.getIdTeam());
        call.enqueue(new Callback<Teams>() {
            @Override
            public void onResponse(Call<Teams> call, retrofit2.Response<Teams> response) {
                if (response.isSuccessful()) {
                    Teams teams = response.body();
                    if (teams != null && teams.getTeams() != null) {
                        team = teams.getTeams().get(0);
                        tvNama.setText(team.getStrTeam());
                        tvDeskripsi.setText(team.getStrDescriptionEN());
                        tvTahun.setText(team.getIntFormedYear());
                        Glide.with(getApplicationContext())
                                .load(team.getStrTeamBadge())
                                .apply(new RequestOptions())
                                .into(imgTeam);
                    }
                }
            }

            @Override
            public void onFailure(Call<Teams> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });

    }

}