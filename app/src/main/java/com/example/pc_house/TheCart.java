package com.example.pc_house;

import android.os.Parcel;
import android.os.Parcelable;

public class TheCart  implements Parcelable {

    private String ID;
    private String Name;
    private double Price;
    private int Qty;
    private String url;

    public TheCart() {
    }

    protected TheCart(Parcel in) {
        ID = in.readString();
        Name = in.readString();
        Price = in.readDouble();
        Qty = in.readInt();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(Name);
        dest.writeDouble(Price);
        dest.writeInt(Qty);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
