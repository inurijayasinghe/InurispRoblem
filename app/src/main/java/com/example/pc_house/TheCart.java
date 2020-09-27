package com.example.pc_house;

import android.os.Parcel;
import android.os.Parcelable;

public class TheCart  implements Parcelable {


    private String userID;
    private String ID;
    private String Name;
    private double Price;
    private int Qty;
    private String url;

    public TheCart() {
    }

    protected TheCart(Parcel in) {
        userID = in.readString();
        ID = in.readString();
        Name = in.readString();
        Price = in.readDouble();
        Qty = in.readInt();
        url = in.readString();
    }

    public static final Creator<TheCart> CREATOR = new Creator<TheCart>() {
        @Override
        public TheCart createFromParcel(Parcel in) {
            return new TheCart(in);
        }

        @Override
        public TheCart[] newArray(int size) {
            return new TheCart[size];
        }
    };

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userID);
        parcel.writeString(ID);
        parcel.writeString(Name);
        parcel.writeDouble(Price);
        parcel.writeInt(Qty);
        parcel.writeString(url);
    }
}
