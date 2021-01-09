package com.pemsel.aoefaa.soccerdb.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Team implements Parcelable {
    @SerializedName("idTeam")
    private String idTeam;
    @SerializedName("strTeam")
    private String strTeam;
    @SerializedName("intFormedYear")
    private String  intFormedYear;
    @SerializedName("strStadium")
    private String strStadium;
    @SerializedName("strDescriptionEN")
    private String strDescriptionEN;
    @SerializedName("strTeamBadge")
    private String strTeamBadge;
    private String favStatus;

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public void setStrTeam(String strTeam) {
        this.strTeam = strTeam;
    }

    public void setIntFormedYear(String intFormedYear) {
        this.intFormedYear = intFormedYear;
    }

    public void setStrStadium(String strStadium) {
        this.strStadium = strStadium;
    }

    public void setStrDescriptionEN(String strDescriptionEN) {
        this.strDescriptionEN = strDescriptionEN;
    }

    public void setStrTeamBadge(String strTeamBadge) {
        this.strTeamBadge = strTeamBadge;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public Team() {
    }

    public Team(String idTeam, String strTeam, String intFormedYear, String strStadium, String strDescriptionEN, String strTeamBadge, String favStatus) {
        this.idTeam = idTeam;
        this.strTeam = strTeam;
        this.intFormedYear = intFormedYear;
        this.strStadium = strStadium;
        this.strDescriptionEN = strDescriptionEN;
        this.strTeamBadge = strTeamBadge;
        this.favStatus = favStatus;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public String getIntFormedYear() {
        return intFormedYear;
    }

    public String getStrStadium() {
        return strStadium;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public String getStrTeamBadge() {
        return strTeamBadge;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idTeam);
        dest.writeString(this.strTeam);
        dest.writeString(this.intFormedYear);
        dest.writeString(this.strStadium);
        dest.writeString(this.strDescriptionEN);
        dest.writeString(this.strTeamBadge);
    }

    protected Team(Parcel in) {
        this.idTeam = in.readString();
        this.strTeam = in.readString();
        this.intFormedYear = in.readString();
        this.strStadium = in.readString();
        this.strDescriptionEN = in.readString();
        this.strTeamBadge = in.readString();
    }

    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}