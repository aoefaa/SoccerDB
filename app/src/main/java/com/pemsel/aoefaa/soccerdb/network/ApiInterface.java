package com.pemsel.aoefaa.soccerdb.network;

import com.pemsel.aoefaa.soccerdb.model.MatchResponse;
import com.pemsel.aoefaa.soccerdb.model.TeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("lookup_all_teams.php")
    Call<TeamResponse> getTeams(
            @Query("id") String id
    );

    @GET("lookupteam.php")
    Call<TeamResponse> getTeamDetail(
            @Query("id") String id
    );


    // Mencari match
    @GET("eventsnextleague.php")
    Call<MatchResponse> getMatches(
            @Query("id") String id
    );

    // Team logo
    @GET("lookupteam.php")
    Call<TeamResponse> getTeamLogo(
            @Query("id") String id
    );

    //Match Detail
    @GET("lookupevent.php")
    Call<MatchResponse> getMatchDetail(
            @Query("id") String id
    );
}
