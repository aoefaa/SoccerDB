package com.pemsel.aoefaa.soccerdb.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteModel implements Parcelable {

    int id;
    private String team_name,
            team_julukan,
            team_year,
            team_stadion,
            team_stadion_kapasitas,
            team_stadion_alamat,
            team_desc,
            team_badge;

    public FavoriteModel(Parcel in) {
        id = in.readInt();
        team_name = in.readString();
        team_julukan = in.readString();
        team_year = in.readString();
        team_stadion = in.readString();
        team_stadion_kapasitas = in.readString();
        team_stadion_alamat = in.readString();
        team_desc = in.readString();
        team_badge = in.readString();
    }

    public static final Creator<FavoriteModel> CREATOR = new Creator<FavoriteModel>() {
        @Override
        public FavoriteModel createFromParcel(Parcel in) {
            return new FavoriteModel(in);
        }

        @Override
        public FavoriteModel[] newArray(int size) {
            return new FavoriteModel[size];
        }
    };

    public FavoriteModel() {

    }

    public String getTeam_julukan() {
        return team_julukan;
    }

    public void setTeam_julukan(String team_julukan) {
        this.team_julukan = team_julukan;
    }

    public String getTeam_stadion() {
        return team_stadion;
    }

    public void setTeam_stadion(String team_stadion) {
        this.team_stadion = team_stadion;
    }

    public String getTeam_stadion_kapasitas() {
        return team_stadion_kapasitas;
    }

    public void setTeam_stadion_kapasitas(String team_stadion_kapasitas) {
        this.team_stadion_kapasitas = team_stadion_kapasitas;
    }

    public String getTeam_stadion_alamat() {
        return team_stadion_alamat;
    }

    public void setTeam_stadion_alamat(String team_stadion_alamat) {
        this.team_stadion_alamat = team_stadion_alamat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_badge() {
        return team_badge;
    }

    public void setTeam_badge(String team_badge) {
        this.team_badge = team_badge;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(team_name);
        dest.writeString(team_julukan);
        dest.writeString(team_year);
        dest.writeString(team_stadion);
        dest.writeString(team_stadion_kapasitas);
        dest.writeString(team_stadion_alamat);
        dest.writeString(team_desc);
        dest.writeString(team_badge);
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
}