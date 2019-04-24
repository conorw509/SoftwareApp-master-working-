package com.example.conor.softwareapp.model;


public class journalContent {
    private String date;
    private String content;
    private String name;

    public journalContent(){

    }

    public journalContent(String date, String content, String tag) {
        this.date = date;
        this.content = content;
        this.name = tag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return name;
    }

    public void setTag(String tag) {
        this.name = tag;
    }
}
