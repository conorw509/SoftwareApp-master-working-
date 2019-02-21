package com.example.conor.softwareapp;

public class User {

    private String userName;
    private String imgageUrl;
    private String id;
    private String status;


    public User(String userName, String imgageUrl, String id, String status) {
        this.userName = userName;
        this.imgageUrl = imgageUrl;
        this.id = id;
        this.status = status;
    }

    public User(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgageUrl() {
        return imgageUrl;
    }

    public void setImgageUrl(String imgageUrl) {
        this.imgageUrl = imgageUrl;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
