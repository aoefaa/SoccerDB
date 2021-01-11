package com.pemsel.aoefaa.soccerdb.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorite implements Parcelable {

    int id;
    private String team_name, team_badge;

    public Favorite(Parcel in) {
        id = in.readInt();
        team_name = in.readString();
        team_badge = in.readString();
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };

    public Favorite() {

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
        dest.writeString(team_badge);
    }
}