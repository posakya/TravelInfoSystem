package com.marr.busreservation.Bases;

/**
 * Created by Posakya on 10/21/2016.
 */
public class Travel_detail {
    private String  Id;
    private String Bus_No;
    private String Driver_No;
    private String Available_Seat;
    private String Destination;
    private String Bus_Category;
    private String Date;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getBus_No() {
        return Bus_No;
    }

    public void setBus_No(String bus_No) {
        Bus_No = bus_No;
    }

    public String getDriver_No() {
        return Driver_No;
    }

    public void setDriver_No(String driver_No) {
        Driver_No = driver_No;
    }

    public String getAvailable_Seat() {
        return Available_Seat;
    }

    public void setAvailable_Seat(String available_Seat) {
        Available_Seat = available_Seat;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getBus_Category() {
        return Bus_Category;
    }

    public void setBus_Category(String bus_Category) {
        Bus_Category = bus_Category;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
