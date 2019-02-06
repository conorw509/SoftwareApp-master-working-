package com.example.conor.softwareapp;

public class music {
    private String songName;
    private String artist;
    private String url;
;

    public music(String songName, String artist){//,String url){
        this.songName = songName;
        this.artist = artist;
       // this.url = url;
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

    public void setArtist(String artist) {
        this.artist = artist;
    }

}
