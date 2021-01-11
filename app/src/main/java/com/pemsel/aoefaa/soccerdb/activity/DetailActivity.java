package com.pemsel.aoefaa.soccerdb.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.adapter.FavoriteAdapter;
import com.pemsel.aoefaa.soccerdb.data.Favorite;
import com.pemsel.aoefaa.soccerdb.data.Teams;
import com.pemsel.aoefaa.soccerdb.data.Team;
import com.pemsel.aoefaa.soccerdb.db.FavoriteDbHelper;
import com.pemsel.aoefaa.soccerdb.network.ApiClient;
import com.pemsel.aoefaa.soccerdb.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private FavoriteDbHelper favoriteDbHelper;
    private Team team = new Team();
    private boolean isFavorite = false;
    private FavoriteAdapter favoriteAdapter;
    private ArrayList<Favorite> favoriteList;
    private Favorite favorite = new Favorite();
    ProgressBar progressBar;
    int IdTeam;
    String NameTeam;
    String YearTeam;
    String DescTeam;
    String BadgeTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        favoriteDbHelper = new FavoriteDbHelper(this);
        favoriteList = favoriteDbHelper.getFavorite();

        TextView tvNama = findViewById(R.id.tvNama);
        TextView tvTahun = findViewById(R.id.tvTahun);
        TextView tvDeskripsi = findViewById(R.id.tvDeskripsi);
        ImageView imgTeam = findViewById(R.id.imgTeam);
        MaterialFavoriteButton btFav = findViewById(R.id.btFav);

        team = getIntent().getParcelableExtra("detail");

        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Teams> call = service.getTeamDetail(String.valueOf(team.getIdTeam()));
        call.enqueue(new Callback<Teams>() {
            @Override
            public void onResponse(Call<Teams> call, Response<Teams> response) {
                if (response.isSuccessful()) {
                    Teams teams = response.body();
                    if (teams != null && teams.getTeams() != null) {
                        team = teams.getTeams().get(0);
                        tvNama.setText(team.getStrTeam());
                        //tvNama.setText(favorite.getTeam_name());
                        tvDeskripsi.setText(team.getStrDescriptionEN());
                        //tvDeskripsi.setText(favorite.getTeam_desc());
                        tvTahun.setText(team.getIntFormedYear());
                        //tvTahun.setText(favorite.getTeam_year());
                        Glide.with(getApplicationContext())
                                .load(team.getStrTeamBadge())
                                .apply(new RequestOptions())
                                .into(imgTeam);

                        //Glide.with(getApplicationContext()).load(favorite.getTeam_badge()).apply(new RequestOptions()).into(imgTeam);

                        for (int i=0;i<favoriteList.size();i++){
                            if (favoriteList.get(i).getTeam_name().equals(team.getStrTeam())){
                                btFav.setFavorite(true);
                            }
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<Teams> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });

        btFav.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {

                        if (favorite) {
                            IdTeam = team.getIdTeam();
                            NameTeam = team.getStrTeam();
                            YearTeam = team.getIntFormedYear();
                            DescTeam = team.getStrDescriptionEN();
                            BadgeTeam = team.getStrTeamBadge();

                            favoriteDbHelper.addFavorite(IdTeam, NameTeam, YearTeam, DescTeam, BadgeTeam);
                            Toast.makeText(DetailActivity.this, "Added", Toast.LENGTH_LONG).show();
                        } else {
                            favoriteDbHelper.delFavorite(IdTeam);
                            Toast.makeText(DetailActivity.this, "Removed", Toast.LENGTH_LONG).show();
                        }

                        }

                }
        );
    }

}