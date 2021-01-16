package com.pemsel.aoefaa.soccerdb.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.adapter.FavoriteAdapter;
import com.pemsel.aoefaa.soccerdb.model.FavoriteModel;
import com.pemsel.aoefaa.soccerdb.model.TeamModel;
import com.pemsel.aoefaa.soccerdb.db.DBHelper;

import java.util.ArrayList;

public class TeamDetailFavoriteActivity extends AppCompatActivity {
    private DBHelper DBHelper;
    private FavoriteModel favoriteModel = new FavoriteModel();
    private TeamModel teamModel = new TeamModel();
    private FavoriteAdapter favoriteAdapter;
    private ArrayList<FavoriteModel> favoriteModelList;
    private ArrayList<TeamModel> teamModelList;

    //Menambahkan ke favorite
    int IdTeam;
    String NameTeam;
    String JulukanTeam;
    String YearTeam;
    String StadionTeam;
    String StadionKapasitasTeam;
    String StadionAlamatTeam;
    String DescTeam;
    String BadgeTeam;

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

        favoriteModel = getIntent().getParcelableExtra("detail");

        if (favoriteModel != null) {
            IdTeam = favoriteModel.getId();
            NameTeam = favoriteModel.getTeam_name();
            JulukanTeam = favoriteModel.getTeam_julukan();
            YearTeam = favoriteModel.getTeam_year();
            StadionTeam = favoriteModel.getTeam_stadion();
            StadionKapasitasTeam = favoriteModel.getTeam_stadion_kapasitas();
            StadionAlamatTeam = favoriteModel.getTeam_stadion_alamat();
            DescTeam = favoriteModel.getTeam_desc();

            tvNama.setText(NameTeam);
            tvJulukan.setText(JulukanTeam);
            tvTahun.setText(YearTeam);
            tvNamaStadion.setText(StadionTeam);
            tvKapasitasStadion.setText(StadionKapasitasTeam);
            tvAlamatStadion.setText(StadionAlamatTeam);
            tvDeskripsi.setText(DescTeam);

            Glide.with(getApplicationContext())
                    .load(favoriteModel.getTeam_badge())
                    .apply(new RequestOptions())
                    .into(imgTeam);

            for (int i = 0; i< favoriteModelList.size(); i++){
                if (favoriteModelList.get(i).getTeam_name().equals(favoriteModelList.get(i).getTeam_name())){
                    btFav.setFavorite(true);
                }
            }
        }

        btFav.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {

                        if (favorite) {
                            IdTeam = TeamDetailFavoriteActivity.this.favoriteModel.getId();
                            NameTeam = TeamDetailFavoriteActivity.this.favoriteModel.getTeam_name();
                            JulukanTeam = TeamDetailFavoriteActivity.this.favoriteModel.getTeam_julukan();
                            YearTeam = TeamDetailFavoriteActivity.this.favoriteModel.getTeam_year();
                            StadionTeam = TeamDetailFavoriteActivity.this.favoriteModel.getTeam_stadion();
                            StadionKapasitasTeam = TeamDetailFavoriteActivity.this.favoriteModel.getTeam_stadion_kapasitas();
                            StadionAlamatTeam = TeamDetailFavoriteActivity.this.favoriteModel.getTeam_stadion_alamat();
                            DescTeam = TeamDetailFavoriteActivity.this.favoriteModel.getTeam_desc();
                            BadgeTeam = TeamDetailFavoriteActivity.this.favoriteModel.getTeam_badge();

                            DBHelper.addFavorite(IdTeam, NameTeam, JulukanTeam, YearTeam, StadionTeam, StadionKapasitasTeam, StadionAlamatTeam, DescTeam, BadgeTeam);
                            Toast.makeText(TeamDetailFavoriteActivity.this, "Ditambahkan", Toast.LENGTH_LONG).show();

                        } else {
                            DBHelper.delFavorite(IdTeam);
                            Toast.makeText(TeamDetailFavoriteActivity.this, "Dihapus", Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }
}