package com.pemsel.aoefaa.soccerdb.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Team implements Parcelable {
    @SerializedName("idTeam")
    private int idTeam;
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

    public Team() {
    }

    public Team(int idTeam, String strTeam, String intFormedYear, String strStadium, String strDescriptionEN, String strTeamBadge, String favStatus) {
        this.idTeam = idTeam;
        this.strTeam = strTeam;
        this.intFormedYear = intFormedYear;
        this.strStadium = strStadium;
        this.strDescriptionEN = strDescriptionEN;
        this.strTeamBadge = strTeamBadge;
        this.favStatus = favStatus;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public String getIntFormedYear() {
        return intFormedYear;
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
        dest.writeInt(this.idTeam);
        dest.writeString(this.strTeam);
        dest.writeString(this.intFormedYear);
        dest.writeString(this.strDescriptionEN);
        dest.writeString(this.strTeamBadge);
    }

    protected Team(Parcel in) {
        this.idTeam = in.readInt();
        this.strTeam = in.readString();
        this.intFormedYear = in.readString();
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