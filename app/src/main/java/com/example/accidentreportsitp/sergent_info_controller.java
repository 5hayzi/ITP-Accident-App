package com.example.accidentreportsitp;

public class sergent_info_controller {
    private static String division, sergeantName, sergeantRank, sergeantId, date, time;

    // Set all data in one method
    public static void setData(String div, String name, String rank, String id, String d, String t) {
        division = div;
        sergeantName = name;
        sergeantRank = rank;
        sergeantId = id;
        date = d;
        time = t;
    }

    // Getters
    public static String getDivision() {
        return division;
    }

    public static String getSergeantName() {
        return sergeantName;
    }

    public static String getSergeantRank() {
        return sergeantRank;
    }

    public static String getSergeantId() {
        return sergeantId;
    }

    public static String getDate() {
        return date;
    }

    public static String getTime() {
        return time;
    }
}

