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
import com.pemsel.aoefaa.soccerdb.adapter.MatchAdapter;
import com.pemsel.aoefaa.soccerdb.model.MatchModel;
import com.pemsel.aoefaa.soccerdb.model.MatchResponse;
import com.pemsel.aoefaa.soccerdb.network.ApiClient;
import com.pemsel.aoefaa.soccerdb.network.ApiInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MatchAdapter matchAdapter;

    List<MatchModel> matchModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_matches);
        matchAdapter = new MatchAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(matchAdapter);

        getDataMatches();
    }

    private void getDataMatches() {
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<MatchResponse> call = service.getMatches("4790");
        call.enqueue(new Callback<MatchResponse>() {

            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse> response) {
                if (response.isSuccessful()) {
                    MatchResponse matchResponse = response.body();
                    if (matchResponse != null && matchResponse.getMatchModels() != null) {
                        matchAdapter.setMatches(matchResponse.getMatchModels());
                        Toast.makeText(getActivity().getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<MatchResponse> call,@NotNull Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}