package com.pemsel.aoefaa.soccerdb.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.activity.MatchDetailActivity;
import com.pemsel.aoefaa.soccerdb.model.MatchModel;
import com.pemsel.aoefaa.soccerdb.model.TeamResponse;
import com.pemsel.aoefaa.soccerdb.network.ApiClient;
import com.pemsel.aoefaa.soccerdb.network.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private List<MatchModel> list = new ArrayList<>();
    private Context context;
    private ImageView ivHomeTeam, ivAwayTeam;
    TeamAdapter teamAdapter;
    ApiInterface apiInterface;

    public MatchAdapter() {

    }

    public MatchAdapter(Context context,ApiInterface apiInterface) {
        this.context = context;
        this.list = new ArrayList<>();
        this.apiInterface = apiInterface;
    }

    public void setMatches(List<MatchModel> list){
        this.context = context;
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MatchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_matches, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchAdapter.ViewHolder holder, int position) {
        final MatchModel matchModel = list.get(position);

        //getHomeLogo(matchModel.getIdHomeTeam());
        getTeamBadge(list.get(position).getIdHomeTeam(), holder.ivHome);
        getTeamBadge(list.get(position).getIdAwayTeam(), holder.ivAway);

        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), MatchDetailActivity.class);
            i.putExtra("detail", list.get(position));
            view.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivHome, ivAway;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivHome = itemView.findViewById(R.id.iv_home);
            ivAway = itemView.findViewById(R.id.iv_away);

            this.itemView = itemView;
        }
    }

    public void getTeamBadge(String teamId, ImageView iv) {
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<TeamResponse> api = service.getTeamDetail(teamId);
        api.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    Picasso.get().load(response.body().getTeamModels().get(0).getStrTeamBadge())
                            .into(iv);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TeamResponse> call, @NonNull Throwable t) {

            }
        });

    }
}