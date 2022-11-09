
package com.example.mei_v.api.model.users;

import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("email_users")
    private String mEmailUser;
    @SerializedName("nama_users")
    private String mNamaUser;
    @SerializedName("role_users")
    private String mRoleUser;
    @SerializedName("username")
    private String mUsername;
    @SerializedName("password")
    private String mPassword;

    public Users(String mRoleUser, String mNamaUser, String mEmailUser, String mUsername){
        this.mRoleUser=mRoleUser;
        this.mNamaUser=mNamaUser;
        this.mEmailUser=mEmailUser;
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public String getEmailUser() {
        return mEmailUser;
    }

    public void setEmailUser(String emailUser) {
        mEmailUser = emailUser;
    }

    public String getNamaUser() {
        return mNamaUser;
    }

    public void setNamaUser(String namaUser) {
        mNamaUser = namaUser;
    }

    public String getRoleUser() {
        return mRoleUser;
    }

    public void setRoleUser(String roleUser) {
        mRoleUser = roleUser;
    }

    public void setmUsername(String username) {
        mUsername = username;
    }

    public String getmUsername() {
        return mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }
}
