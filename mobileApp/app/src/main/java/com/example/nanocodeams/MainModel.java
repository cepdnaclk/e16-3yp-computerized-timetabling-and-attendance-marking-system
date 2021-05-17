package com.example.nanocodeams;

public class MainModel {

    String time;
    String room;
    String sub;

    public MainModel(String time, String room, String sub) {
        this.time = time;
        this.room = room;
        this.sub = sub;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
