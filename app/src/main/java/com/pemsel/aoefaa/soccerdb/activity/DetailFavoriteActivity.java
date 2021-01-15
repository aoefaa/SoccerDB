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

public class DetailFavoriteActivity extends AppCompatActivity {
    private DBHelper DBHelper;
    private FavoriteModel favoriteModel = new FavoriteModel();
    private TeamModel teamModel = new TeamModel();
    private FavoriteAdapter favoriteAdapter;
    private ArrayList<FavoriteModel> favoriteModelList;
    private ArrayList<TeamModel> teamModelList;
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

        favoriteModel = getIntent().getParcelableExtra("detail");

        if (favoriteModel != null) {
            IdTeam = favoriteModel.getId();
            NameTeam = favoriteModel.getTeam_name();
            YearTeam = favoriteModel.getTeam_year();
            DescTeam = favoriteModel.getTeam_desc();

            tvNama.setText(NameTeam);
            tvTahun.setText(YearTeam);
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
                            IdTeam = DetailFavoriteActivity.this.favoriteModel.getId();
                            NameTeam = DetailFavoriteActivity.this.favoriteModel.getTeam_name();
                            YearTeam = DetailFavoriteActivity.this.favoriteModel.getTeam_year();
                            DescTeam = DetailFavoriteActivity.this.favoriteModel.getTeam_desc();
                            BadgeTeam = DetailFavoriteActivity.this.favoriteModel.getTeam_badge();

                            DBHelper.addFavorite(IdTeam, NameTeam, YearTeam, DescTeam, BadgeTeam);
                            Toast.makeText(DetailFavoriteActivity.this, "Added", Toast.LENGTH_LONG).show();

                        } else {
                            DBHelper.delFavorite(IdTeam);
                            Toast.makeText(DetailFavoriteActivity.this, "Removed", Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }
}