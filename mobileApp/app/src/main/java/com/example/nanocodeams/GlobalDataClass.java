package com.example.nanocodeams;

import org.json.JSONArray;

public class GlobalDataClass {

    private static JSONArray timeTable;
    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        GlobalDataClass.token = token;
    }

    public static JSONArray getTimeTable() {
        return timeTable;
    }

    public static void setTimeTable(JSONArray timeTable) {
        GlobalDataClass.timeTable = timeTable;
    }
}
