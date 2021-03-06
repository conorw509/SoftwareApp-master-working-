package com.example.conor.softwareapp.model;

import com.google.firebase.database.PropertyName;

public class messages {

    private String msg;
    private String send;
    private String recieve;
    private boolean isSeen;

    public messages(String msg, String send, String recieve, boolean isSeen) {
        this.msg = msg;
        this.send = send;
        this.recieve = recieve;
        this.isSeen = isSeen;
    }

    public messages() {

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getRecieve() {
        return recieve;
    }

    public void setRecieve(String recieve) {
        this.recieve = recieve;
    }

    @PropertyName("isSeen")
    public boolean isSeen() {
        return this.isSeen;
    }

    public void setSeen(boolean seen) {
       this.isSeen = seen;
    }
}
