package com.pemsel.aoefaa.soccerdb.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.adapter.MatchAdapter;
import com.pemsel.aoefaa.soccerdb.model.MatchModel;
import com.pemsel.aoefaa.soccerdb.model.MatchResponse;
import com.pemsel.aoefaa.soccerdb.model.TeamResponse;
import com.pemsel.aoefaa.soccerdb.network.ApiClient;
import com.pemsel.aoefaa.soccerdb.network.ApiInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchDetailActivity extends AppCompatActivity {

    private MatchModel matchModel = new MatchModel();
    private final TeamResponse teamResponse = new TeamResponse();
    private MatchAdapter matchAdapter = new MatchAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_match);

        TextView tvHomeTeam = findViewById(R.id.tv_home_team);
        TextView tvAwayTeam = findViewById(R.id.tv_away_team);
        TextView tvWaktuMatch = findViewById(R.id.tv_waktu_match);
        TextView tvDateMatch = findViewById(R.id.tv_date_match);
        TextView tvStadionMatch = findViewById(R.id.tv_stadion_match);

        ImageView ivHomeTeam = findViewById(R.id.iv_home_team);
        ImageView ivAwayTeam = findViewById(R.id.iv_away_team);

        matchModel = getIntent().getParcelableExtra("detail");

        matchAdapter.getTeamBadge(matchModel.getIdHomeTeam(), ivHomeTeam);
        matchAdapter.getTeamBadge(matchModel.getIdAwayTeam(), ivAwayTeam);

        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<MatchResponse> call = service.getMatchDetail(matchModel.getIdEvent());
        call.enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {
                if (response.isSuccessful()) {
                    MatchResponse matchResponse = response.body();
                    if (matchResponse != null && matchResponse.getMatchModels() != null) {
                        matchModel = matchResponse.getMatchModels().get(0);

                        tvHomeTeam.setText(matchModel.getStrHomeTeam());
                        tvAwayTeam.setText(matchModel.getStrAwayTeam());
                        tvWaktuMatch.setText(matchModel.getStrTime());
                        tvDateMatch.setText(matchModel.getDateEvent());
                        tvStadionMatch.setText(matchModel.getStrVenue());
                    }
                }
            }

            @Override
            public void onFailure(Call<MatchResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
    }
}