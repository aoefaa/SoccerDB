package com.pemsel.aoefaa.soccerdb.data;

public class Favorite {

    private String id;
    private String team_name;
    private String team_year;
    private String team_desc;
    private int team_badge;
    private String fav_status;

    public Favorite() {
    }

    public Favorite(String id,
                    String team_name,
                    String team_year,
                    String team_desc,
                    int team_badge,
                    String fav_status) {
        this.id = id;
        this.team_name = team_name;
        this.team_year = team_year;
        this.team_desc = team_desc;
        this.team_badge = team_badge;
        this.fav_status = fav_status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_year() {
        return team_year;
    }

    public void setTeam_year(String team_year) {
        this.team_year = team_year;
    }

    public String getTeam_desc() {
        return team_desc;
    }

    public void setTeam_desc(String team_desc) {
        this.team_desc = team_desc;
    }

    public int getTeam_badge() {
        return team_badge;
    }

    public void setTeam_badge(int team_badge) {
        this.team_badge = team_badge;
    }

    public String getFav_status() {
        return fav_status;
    }

    public void setFav_status(String fav_status) {
        this.fav_status = fav_status;
    }
}