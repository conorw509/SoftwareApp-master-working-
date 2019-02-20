package com.example.conor.softwareapp;

public class messages {

    private String msg;
    private String send;
    private String recieve;

    public messages(String msg, String send, String recieve) {
        this.msg = msg;
        this.send = send;
        this.recieve = recieve;
    }

    public messages(){

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
}
