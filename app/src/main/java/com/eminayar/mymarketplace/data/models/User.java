package com.eminayar.mymarketplace.data.models;

public class User {

    private String mUserName;
    private String mPassword;

    public User() {
    }

    public User(String mUserName, String mPassword) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    // Usually passwords have limitation to be have min charachter length
    public boolean isPasswordLongEnough() {
        return getPassword().length() > 5;
    }

    //
    public boolean isUsernamePasswordCorrect() {
        //Because we will provide dummy login, I just put these strings hardcoded here
        return getUserName().trim().equals("kariyer") && getPassword().trim().equals("2019ADev");
    }
}