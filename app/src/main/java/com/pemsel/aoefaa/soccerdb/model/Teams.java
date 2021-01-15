package com.pemsel.aoefaa.soccerdb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Teams {
    @SerializedName("teamModels")
    private List<TeamModel> teamModels;

    public Teams() {
    }

    public Teams(List<TeamModel> teamModels) {
        this.teamModels = teamModels;
    }

    public List<TeamModel> getTeamModels() {
        return teamModels;
    }
}
