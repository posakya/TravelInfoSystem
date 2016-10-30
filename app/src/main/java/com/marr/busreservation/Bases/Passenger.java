package com.marr.busreservation.Bases;

/**
 * Created by Posakya on 10/30/2016.
 */
public class Passenger {
    private String Id;
    private String Passenger_Name;
    private String Seat_No;
    private String Phone;
    private String Destination;
    private String Bus_No;
    private String Bus_Category;
    private String Date;
    private String Time;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPassenger_Name() {
        return Passenger_Name;
    }

    public void setPassenger_Name(String passenger_Name) {
        Passenger_Name = passenger_Name;
    }

    public String getSeat_No() {
        return Seat_No;
    }

    public void setSeat_No(String seat_No) {
        Seat_No = seat_No;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getBus_No() {
        return Bus_No;
    }

    public void setBus_No(String bus_No) {
        Bus_No = bus_No;
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

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
