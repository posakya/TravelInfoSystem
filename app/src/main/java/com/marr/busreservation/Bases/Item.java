package com.marr.busreservation.Bases;

/**
 * Created by Posakya on 8/10/2016.
 */
public class Item {
    private String Id;
    private String Name;
    private String Image;
    private int Type;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }



}
