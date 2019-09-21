package com.eminayar.mymarketplace.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductDetail implements Parcelable {

    @SerializedName("orderDetail")
    private String orderDetail;
    @SerializedName("summaryPrice")
    private float summaryPrice;

    public ProductDetail() {
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public float getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(float summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public String getFormattedPrice () {
        return this.summaryPrice + " TRY";
    }

    protected ProductDetail(Parcel in) {
        orderDetail = in.readString();
        summaryPrice = in.readFloat();
    }

    public static final Creator<ProductDetail> CREATOR = new Creator<ProductDetail>() {
        @Override
        public ProductDetail createFromParcel(Parcel in) {
            return new ProductDetail(in);
        }

        @Override
        public ProductDetail[] newArray(int size) {
            return new ProductDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(orderDetail);
        parcel.writeFloat(summaryPrice);
    }
}
