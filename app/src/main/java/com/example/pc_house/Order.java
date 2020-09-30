package com.example.pc_house;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable  {

    private int price;
    private String id; 
    private String name; 
    private String url;
    private int qty;


    public Order() {
    }

    protected Order(Parcel in) {
        price = in.readInt();
        id = in.readString();
        name = in.readString();
        url = in.readString();
        qty = in.readInt();

    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public static Creator<Order> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(price);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeInt(qty);

    }
}
