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
import com.pemsel.aoefaa.soccerdb.data.Favorite;
import com.pemsel.aoefaa.soccerdb.data.Team;
import com.pemsel.aoefaa.soccerdb.data.Teams;
import com.pemsel.aoefaa.soccerdb.db.FavoriteDbHelper;
import com.pemsel.aoefaa.soccerdb.network.ApiClient;
import com.pemsel.aoefaa.soccerdb.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFavoriteActivity extends AppCompatActivity {
    private FavoriteDbHelper favoriteDbHelper;
    private Favorite favorite = new Favorite();
    private Team team = new Team();
    private FavoriteAdapter favoriteAdapter;
    private ArrayList<Favorite> favoriteList;
    private ArrayList<Team> teamList;
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

        favorite = getIntent().getParcelableExtra("detail");

        if (favorite != null) {
            IdTeam = favorite.getId();
            NameTeam = favorite.getTeam_name();
            YearTeam = favorite.getTeam_year();
            DescTeam = favorite.getTeam_desc();

            tvNama.setText(NameTeam);
            tvTahun.setText(YearTeam);
            tvDeskripsi.setText(DescTeam);

            Glide.with(getApplicationContext())
                    .load(favorite.getTeam_badge())
                    .apply(new RequestOptions())
                    .into(imgTeam);

            for (int i=0;i<favoriteList.size();i++){
                if (favoriteList.get(i).getTeam_name().equals(favoriteList.get(i).getTeam_name())){
                    btFav.setFavorite(true);
                }
            }
        }

        btFav.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {

                        if (favorite) {
                            IdTeam = DetailFavoriteActivity.this.favorite.getId();
                            NameTeam = DetailFavoriteActivity.this.favorite.getTeam_name();
                            YearTeam = DetailFavoriteActivity.this.favorite.getTeam_year();
                            DescTeam = DetailFavoriteActivity.this.favorite.getTeam_desc();
                            BadgeTeam = DetailFavoriteActivity.this.favorite.getTeam_badge();

                            favoriteDbHelper.addFavorite(IdTeam, NameTeam, YearTeam, DescTeam, BadgeTeam);
                            Toast.makeText(DetailFavoriteActivity.this, "Added", Toast.LENGTH_LONG).show();

                        } else {
                            favoriteDbHelper.delFavorite(IdTeam);
                            Toast.makeText(DetailFavoriteActivity.this, "Removed", Toast.LENGTH_LONG).show();

                        }

                        }

                }
        );
    }

}