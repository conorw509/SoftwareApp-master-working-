package com.example.conor.softwareapp.model;

import java.util.Date;

public class journalContent {
    private long date;
    private String content;
    private String tag;

    public journalContent(){

    }

    public journalContent(long date, String content, String tag) {
        this.date = date;
        this.content = content;
        this.tag = tag;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
