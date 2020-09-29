package com.example.pc_house;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable  {

    private int AddressId;
    private String Country;
    private String Street_Address;
    private String Cty;
    private String Province;
    private int postalCode;
    private int Telephone;

    public Order() {
    }

    protected Order(Parcel in) {
        AddressId = in.readInt();
        Country = in.readString();
        Street_Address = in.readString();
        Cty = in.readString();
        Province = in.readString();
        postalCode = in.readInt();
        Telephone = in.readInt();
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
    public int getAddressId() {
        return AddressId;
    }

    public void setAddressId(int addressId) {
        AddressId = addressId;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getStreet_Address() {
        return Street_Address;
    }

    public void setStreet_Address(String street_Address) {
        Street_Address = street_Address;
    }

    public String getCty() {
        return Cty;
    }

    public void setCty(String cty) {
        Cty = cty;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public int getTelephone() {
        return Telephone;
    }

    public void setTelephone(int telephone) {
        Telephone = telephone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(AddressId);
        dest.writeString(Country);
        dest.writeString(Street_Address);
        dest.writeString(Cty);
        dest.writeString(Province);
        dest.writeInt(postalCode);
        dest.writeInt(Telephone);
    }
}
