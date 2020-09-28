package com.example.pc_house;

import android.os.Parcel;
import android.os.Parcelable;

public class Orders implements Parcelable {
    private int price;
    private String id;
    private String name;
    private String qty;
    private String url;

    //test test test test


    public Orders() {
    }

    protected Orders(Parcel in) {
        price = in.readInt();
        id = in.readString();
        name = in.readString();
        qty = in.readString();
        url = in.readString();

    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Creator<Address> getCREATOR() {
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
        dest.writeString(qty);
        dest.writeString(url);

    }
}


