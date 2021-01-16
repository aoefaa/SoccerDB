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
import com.pemsel.aoefaa.soccerdb.model.TeamResponse;
import com.pemsel.aoefaa.soccerdb.model.TeamModel;
import com.pemsel.aoefaa.soccerdb.db.DBHelper;
import com.pemsel.aoefaa.soccerdb.network.ApiClient;
import com.pemsel.aoefaa.soccerdb.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamDetailActivity extends AppCompatActivity {
    private DBHelper DBHelper;
    private TeamModel teamModel = new TeamModel();
    private boolean isFavorite = false;
    private FavoriteAdapter favoriteAdapter;
    private FavoriteModel favoriteModel = new FavoriteModel();
    ProgressBar progressBar;

    //Bring to Favorite
    int IdTeam;
    String NameTeam;
    String JulukanTeam;
    String YearTeam;
    String StadionNameTeam;
    String KapasitasStadionTeam;
    String AlamatStadionTeam;
    String DescTeam;
    String BadgeTeam;
    private ArrayList<FavoriteModel> favoriteModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_team);

        DBHelper = new DBHelper(this);
        favoriteModelList = DBHelper.getFavorite();

        //TextView
        TextView tvNama = findViewById(R.id.tv_nama_team);
        TextView tvTahun = findViewById(R.id.tv_tahun_team);
        TextView tvDeskripsi = findViewById(R.id.tv_deskripsi_team);
        TextView tvJulukan = findViewById(R.id.tv_julukan_team);
        TextView tvNamaStadion = findViewById(R.id.tv_stadion);
        TextView tvKapasitasStadion = findViewById(R.id.tv_kapasitas_stadion);
        TextView tvAlamatStadion = findViewById(R.id.tv_alamat_stadion);
        //ImageView
        ImageView imgTeam = findViewById(R.id.iv_team);
        //Button
        MaterialFavoriteButton btFav = findViewById(R.id.bt_favorite);

        teamModel = getIntent().getParcelableExtra("detail");

        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<TeamResponse> call = service.getTeamDetail(String.valueOf(teamModel.getIdTeam()));
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    TeamResponse teamResponse = response.body();
                    if (teamResponse != null && teamResponse.getTeamModels() != null) {
                        teamModel = teamResponse.getTeamModels().get(0);
                        tvNama.setText(teamModel.getStrTeam());
                        tvJulukan.setText(teamModel.getStrKeywords());
                        tvTahun.setText(teamModel.getIntFormedYear());
                        tvNamaStadion.setText(teamModel.getStrStadium());
                        tvKapasitasStadion.setText(teamModel.getIntStadiumCapacity());
                        tvAlamatStadion.setText(teamModel.getStrStadiumLocation());
                        tvDeskripsi.setText(teamModel.getStrDescriptionEN());

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
            public void onFailure(Call<TeamResponse> call, Throwable t) {
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
                            JulukanTeam = teamModel.getStrKeywords();
                            StadionNameTeam = teamModel.getStrStadium();
                            KapasitasStadionTeam = teamModel.getIntStadiumCapacity();
                            AlamatStadionTeam = teamModel.getStrStadiumLocation();
                            YearTeam = teamModel.getIntFormedYear();
                            DescTeam = teamModel.getStrDescriptionEN();
                            BadgeTeam = teamModel.getStrTeamBadge();

                            DBHelper.addFavorite(IdTeam, NameTeam, JulukanTeam, YearTeam, StadionNameTeam, KapasitasStadionTeam, AlamatStadionTeam, DescTeam, BadgeTeam);
                            Toast.makeText(TeamDetailActivity.this, "Ditambahkan", Toast.LENGTH_LONG).show();
                        } else {
                            DBHelper.delFavorite(IdTeam);
                            Toast.makeText(TeamDetailActivity.this, "Dihapus", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}