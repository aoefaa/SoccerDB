package com.pemsel.aoefaa.soccerdb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchResponse {
    @SerializedName("events")
    private List<MatchModel> matchModels;

    public MatchResponse() {
    }

    public MatchResponse(List<MatchModel> matchModels) {
        this.matchModels = matchModels;
    }

    public List<MatchModel> getMatchModels() {
        return matchModels;
    }
}

