package com.pemsel.aoefaa.soccerdb.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.adapter.TeamAdapter;
import com.pemsel.aoefaa.soccerdb.model.TeamResponse;
import com.pemsel.aoefaa.soccerdb.network.ApiClient;
import com.pemsel.aoefaa.soccerdb.network.ApiInterface;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsFragment extends Fragment {
    private RecyclerView recyclerView;
    private TeamAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvTeams);
        adapter = new TeamAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        getDataTeams();
    }

    private void getDataTeams() {
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<TeamResponse> call = service.getTeams("4790");
        call.enqueue(new Callback<TeamResponse>() {

            @Override
            public void onResponse(@NotNull Call<TeamResponse> call, @NotNull Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    TeamResponse teamResponse = response.body();
                    if (teamResponse != null && teamResponse.getTeamModels() != null) {
                        adapter.setData(teamResponse.getTeamModels());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<TeamResponse> call, @NotNull Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
            }

        });
    }
}