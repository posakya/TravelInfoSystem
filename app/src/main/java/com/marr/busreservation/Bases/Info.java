package com.marr.busreservation.Bases;

/**
 * Created by Posakya on 10/30/2016.
 */
public class Info {
    private String Id;
    private String Arrival_Time;
    private String Departure_Time;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getArrival_Time() {
        return Arrival_Time;
    }

    public void setArrival_Time(String arrival_Time) {
        Arrival_Time = arrival_Time;
    }

    public String getDeparture_Time() {
        return Departure_Time;
    }

    public void setDeparture_Time(String departure_Time) {
        Departure_Time = departure_Time;
    }
}
