package com.example.pc_house;

public class Orders {

    String name;
    String details;
    String imageurl;

    public Orders() {
    }

    public Orders(String name, String details, String imageurl) {
        this.name = name;
        this.details = details;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
