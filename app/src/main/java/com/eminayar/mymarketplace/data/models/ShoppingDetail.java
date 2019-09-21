package com.eminayar.mymarketplace.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ShoppingDetail implements Parcelable {

    @SerializedName("date")
    private String date;
    @SerializedName("month")
    private String month;
    @SerializedName("marketName")
    private String marketName;
    @SerializedName("orderName")
    private String orderName;
    @SerializedName("productPrice")
    private float productPrice;
    @SerializedName("productState")
    private String productState;
    @SerializedName("productDetail")
    private ProductDetail productDetail;

    protected ShoppingDetail(Parcel in) {
        date = in.readString();
        month = in.readString();
        marketName = in.readString();
        orderName = in.readString();
        productPrice = in.readFloat();
        productState = in.readString();
        productDetail = in.readParcelable(ProductDetail.class.getClassLoader());
    }

    public ShoppingDetail() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public static final Creator<ShoppingDetail> CREATOR = new Creator<ShoppingDetail>() {
        @Override
        public ShoppingDetail createFromParcel(Parcel in) {
            return new ShoppingDetail(in);
        }

        @Override
        public ShoppingDetail[] newArray(int size) {
            return new ShoppingDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(month);
        parcel.writeString(marketName);
        parcel.writeString(orderName);
        parcel.writeFloat(productPrice);
        parcel.writeString(productState);
    }
}
