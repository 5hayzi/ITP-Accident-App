package com.example.accidentreportsitp;

public class death_screen_controller {
    private static String death_descp, injury_descp, officer_duty, police, hospital;
    private static String death_No, injury_No, FIR, compromise;

    public static void set_data(String death, String injury, String officer,String p,String h,
                                String deathNo, String injuryNo, String fir, String comp){
        death_descp = death;
        injury_descp = injury;
        officer_duty = officer;
        police = p;
        hospital = h;
        death_No = deathNo;
        injury_No = injuryNo;
        FIR = fir;
        compromise = comp;
    }

    public static String getDeath_descp() {
        return death_descp;
    }

    public static String getInjury_descp() {
        return injury_descp;
    }

    public static String getOfficer_duty() {
        return officer_duty;
    }

    public static String getPolice() {
        return police;
    }

    public static String getHospital() {
        return hospital;
    }

    public static String getDeath_No() {
        return death_No;
    }

    public static String getInjury_No() {
        return injury_No;
    }

    public static String getFIR() {
        return FIR;
    }

    public static String getCompromise() {
        return compromise;
    }
}
