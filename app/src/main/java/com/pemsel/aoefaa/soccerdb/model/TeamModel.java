package com.pemsel.aoefaa.soccerdb.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamModel implements Parcelable {
    @SerializedName("idTeam")
    @Expose
    private int idTeam;
    @SerializedName("strTeam")
    @Expose
    private String strTeam;
    @SerializedName("intFormedYear")
    @Expose
    private String intFormedYear;
    @SerializedName("strStadium")
    @Expose
    private String strStadium;
    @SerializedName("strKeywords")
    @Expose
    private String strKeywords;
    @SerializedName("strStadiumLocation")
    @Expose
    private String strStadiumLocation;
    @SerializedName("intStadiumCapacity")
    @Expose
    private String intStadiumCapacity;
    @SerializedName("strWebsite")
    @Expose
    private String strWebsite;
    @SerializedName("strFacebook")
    @Expose
    private String strFacebook;
    @SerializedName("strTwitter")
    @Expose
    private String strTwitter;
    @SerializedName("strInstagram")
    @Expose
    private String strInstagram;
    @SerializedName("strDescriptionEN")
    @Expose
    private String strDescriptionEN;

    @SerializedName("strGender")
    @Expose
    private String strGender;
    @SerializedName("strCountry")
    @Expose
    private String strCountry;
    @SerializedName("strTeamBadge")
    @Expose
    private String strTeamBadge;
    @SerializedName("strYoutube")
    @Expose
    private String strYoutube;

    public TeamModel() {
    }

    public String getStrStadium() {
        return strStadium;
    }

    public String getStrKeywords() {
        return strKeywords;
    }

    public String getStrStadiumLocation() {
        return strStadiumLocation;
    }

    public String getIntStadiumCapacity() {
        return intStadiumCapacity;
    }

    public String getStrWebsite() {
        return strWebsite;
    }

    public String getStrFacebook() {
        return strFacebook;
    }

    public String getStrTwitter() {
        return strTwitter;
    }

    public String getStrInstagram() {
        return strInstagram;
    }

    public String getStrGender() {
        return strGender;
    }

    public String getStrCountry() {
        return strCountry;
    }

    public String getStrYoutube() {
        return strYoutube;
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

    protected TeamModel(Parcel in) {
        this.idTeam = in.readInt();
        this.strTeam = in.readString();
        this.intFormedYear = in.readString();
        this.strDescriptionEN = in.readString();
        this.strTeamBadge = in.readString();
    }

    public static final Parcelable.Creator<TeamModel> CREATOR = new Parcelable.Creator<TeamModel>() {
        @Override
        public TeamModel createFromParcel(Parcel source) {
            return new TeamModel(source);
        }

        @Override
        public TeamModel[] newArray(int size) {
            return new TeamModel[size];
        }
    };
}