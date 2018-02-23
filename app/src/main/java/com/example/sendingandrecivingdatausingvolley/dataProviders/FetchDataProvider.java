package com.example.sendingandrecivingdatausingvolley.dataProviders;

/**
 * Created by Anonymous on 1/9/2018.
 */
public class FetchDataProvider {

    private String Date, Time, Details;

    public FetchDataProvider(String date, String time, String details) {
        Date = date;
        Time = time;
        Details = details;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
