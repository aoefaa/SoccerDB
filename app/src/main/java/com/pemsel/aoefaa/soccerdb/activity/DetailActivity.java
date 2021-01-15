package com.pemsel.aoefaa.soccerdb.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.adapter.FavoriteAdapter;
import com.pemsel.aoefaa.soccerdb.model.FavoriteModel;
import com.pemsel.aoefaa.soccerdb.model.Teams;
import com.pemsel.aoefaa.soccerdb.model.TeamModel;
import com.pemsel.aoefaa.soccerdb.db.DBHelper;
import com.pemsel.aoefaa.soccerdb.network.ApiClient;
import com.pemsel.aoefaa.soccerdb.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private DBHelper DBHelper;
    private TeamModel teamModel = new TeamModel();
    private boolean isFavorite = false;
    private FavoriteAdapter favoriteAdapter;
    private ArrayList<FavoriteModel> favoriteModelList;
    private FavoriteModel favoriteModel = new FavoriteModel();
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

        DBHelper = new DBHelper(this);
        favoriteModelList = DBHelper.getFavorite();

        TextView tvNama = findViewById(R.id.tvNama);
        TextView tvTahun = findViewById(R.id.tvTahun);
        TextView tvDeskripsi = findViewById(R.id.tvDeskripsi);
        ImageView imgTeam = findViewById(R.id.imgTeam);
        MaterialFavoriteButton btFav = findViewById(R.id.btFav);

        teamModel = getIntent().getParcelableExtra("detail");

        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Teams> call = service.getTeamDetail(String.valueOf(teamModel.getIdTeam()));
        call.enqueue(new Callback<Teams>() {
            @Override
            public void onResponse(Call<Teams> call, Response<Teams> response) {
                if (response.isSuccessful()) {
                    Teams teams = response.body();
                    if (teams != null && teams.getTeamModels() != null) {
                        teamModel = teams.getTeamModels().get(0);
                        tvNama.setText(teamModel.getStrTeam());
                        tvDeskripsi.setText(teamModel.getStrDescriptionEN());
                        tvTahun.setText(teamModel.getIntFormedYear());
                        Glide.with(getApplicationContext())
                                .load(teamModel.getStrTeamBadge())
                                .apply(new RequestOptions())
                                .into(imgTeam);

                        for (int i = 0; i< favoriteModelList.size(); i++){
                            if (favoriteModelList.get(i).getTeam_name().equals(teamModel.getStrTeam())){
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
                            IdTeam = teamModel.getIdTeam();
                            NameTeam = teamModel.getStrTeam();
                            YearTeam = teamModel.getIntFormedYear();
                            DescTeam = teamModel.getStrDescriptionEN();
                            BadgeTeam = teamModel.getStrTeamBadge();

                            DBHelper.addFavorite(IdTeam, NameTeam, YearTeam, DescTeam, BadgeTeam);
                            Toast.makeText(DetailActivity.this, "Added", Toast.LENGTH_LONG).show();
                        } else {
                            DBHelper.delFavorite(IdTeam);
                            Toast.makeText(DetailActivity.this, "Removed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}