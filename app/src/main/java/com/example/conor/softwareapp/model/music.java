package com.example.conor.softwareapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class music implements Parcelable {
    private String songName;
    private String artist;

    public music(String songName, String artist) {
        this.songName = songName;
        this.artist = artist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.songName);
        dest.writeString(this.artist);
    }

    protected music(Parcel in) {
        this.songName = in.readString();
        this.artist = in.readString();
    }

    public static final Parcelable.Creator<music> CREATOR = new Parcelable.Creator<music>() {
        @Override
        public music createFromParcel(Parcel source) {
            return new music(source);
        }

        @Override
        public music[] newArray(int size) {
            return new music[size];
        }
    };


}
