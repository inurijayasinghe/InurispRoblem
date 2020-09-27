package com.example.pc_house;

import android.os.Parcel;
import android.os.Parcelable;

public class Payment implements Parcelable {

    private int PaymentId;
    private String customer_name;
    private String cardNo;
    private int cvv;
    private String expireDate;

    public Payment() {
    }

    protected Payment(Parcel in) {
        PaymentId = in.readInt();
        customer_name = in.readString();
        cardNo = in.readString();
        cvv = in.readInt();
        expireDate = in.readString();
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel in) {
            return new Payment(in);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };

    public int getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(int paymentId) {
        PaymentId = paymentId;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(PaymentId);
        dest.writeString(customer_name);
        dest.writeString(cardNo);
        dest.writeInt(cvv);
        dest.writeString(expireDate);
    }
}


