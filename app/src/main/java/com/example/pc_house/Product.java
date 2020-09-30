package com.example.pc_house;


import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int ProductID;
    private String ProductName;
    private String ProductCategory;
    private String ProductDescription;
    private String Price;


    public Product() {
    }

    protected Product(Parcel in) {
        ProductID = in.readInt();
        ProductName = in.readString();
        ProductCategory = in.readString();
        ProductDescription = in.readString();
        Price = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ProductID);
        parcel.writeString(ProductName);
        parcel.writeString(ProductCategory);
        parcel.writeString(ProductDescription);
        parcel.writeString(Price);
    }
}

