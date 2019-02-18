package com.example.conor.softwareapp;

public class User {

    private String userName;
    private String imgageUrl;
    private String id;

//    public User(String userName, String imgUrl, String id) {
//        this.userName = userName;
//        this.imgUrl = imgUrl;
//        this.id = id;
//    }

    public User(){

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
