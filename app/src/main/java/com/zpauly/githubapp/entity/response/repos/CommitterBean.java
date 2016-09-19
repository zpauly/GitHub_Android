package com.zpauly.githubapp.entity.response.repos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 16/9/19.
 */
public class CommitterBean implements Parcelable {
    private String name;
    private String email;
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.date);
    }

    public CommitterBean() {
    }

    protected CommitterBean(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<CommitterBean> CREATOR = new Parcelable.Creator<CommitterBean>() {
        @Override
        public CommitterBean createFromParcel(Parcel source) {
            return new CommitterBean(source);
        }

        @Override
        public CommitterBean[] newArray(int size) {
            return new CommitterBean[size];
        }
    };
}
