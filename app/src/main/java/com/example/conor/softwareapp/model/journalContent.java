package com.example.conor.softwareapp.model;


public class journalContent {
    private String Date;
    private String Content;
;

    public journalContent(){

    }

    public journalContent(String Date, String Content) {
        this.Date = Date;
        this.Content = Content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

}
