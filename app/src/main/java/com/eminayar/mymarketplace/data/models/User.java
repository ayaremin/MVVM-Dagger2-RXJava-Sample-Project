package com.eminayar.mymarketplace.data.models;

public class User {

    private String mUserName;
    private String mPassword;
    private boolean mCredentialsRemembered;


    public User() {
    }

    public User(String mUserName, String mPassword, boolean mCredentialsRemembered) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mCredentialsRemembered = mCredentialsRemembered;
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

    public boolean isCredentialsRemembered() {
        return mCredentialsRemembered;
    }

    public void setCredentialsRemembered(boolean mCredentialsRemembered) {
        this.mCredentialsRemembered = mCredentialsRemembered;
    }

    // Usually passwords have limitation to be have min charachter length
    public boolean isPasswordLongEnough() {
        return getPassword().length() > 5;
    }

    //
    public boolean isUsernamePasswordCorrect() {
        //Because we will provide dummy login, I just put these strings hardcoded here
        if (getUserName().equals("kariyer”") & getPassword().equals("2019ADev")) {
            return true;
        }
        return false;
    }
}