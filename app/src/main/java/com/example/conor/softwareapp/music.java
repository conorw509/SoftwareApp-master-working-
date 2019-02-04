package com.example.conor.softwareapp;

public class music {
    private String songName;
    private String artist;
    private String imagePlay_Pause;
    private String imageStop;

    public music(String songName, String artist){//String imagePlay_Pause, String imageStop) {
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

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImagePlay_Pause() {
        return imagePlay_Pause;
    }

    public void setImagePlay_Pause(String imagePlay_Pause) {
        this.imagePlay_Pause = imagePlay_Pause;
    }

    public String getImageStop() {
        return imageStop;
    }

    public void setImageStop(String imageStop) {
        this.imageStop = imageStop;
    }
}
