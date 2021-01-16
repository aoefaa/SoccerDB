package com.pemsel.aoefaa.soccerdb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamResponse {
    @SerializedName("teams")
    private List<TeamModel> teamModels;

    public TeamResponse() {
    }

    public TeamResponse(List<TeamModel> teamModels) {
        this.teamModels = teamModels;
    }

    public List<TeamModel> getTeamModels() {
        return teamModels;
    }
}
