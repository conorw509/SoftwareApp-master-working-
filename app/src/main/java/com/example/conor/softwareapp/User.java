package com.example.conor.softwareapp;

public class User {

    private String userName;
    private String id;
    private String status;
    private String education;
    private String about;
    private String address;
    private String imageUrl;
    private String search;

    public User(String userName, String id, String status, String education, String about, String address, String imageUrl, String search) {
        this.userName = userName;
        this.id = id;
        this.status = status;
        this.education = education;
        this.about = about;
        this.address = address;
        this.imageUrl = imageUrl;
        this.search = search;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
