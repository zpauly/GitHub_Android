package com.zpauly.githubapp.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 16-8-9.
 */

public class PermissionsBean implements Parcelable {
    private boolean admin;
    private boolean push;
    private boolean pull;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }

    public boolean isPull() {
        return pull;
    }

    public void setPull(boolean pull) {
        this.pull = pull;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.admin ? (byte) 1 : (byte) 0);
        dest.writeByte(this.push ? (byte) 1 : (byte) 0);
        dest.writeByte(this.pull ? (byte) 1 : (byte) 0);
    }

    public PermissionsBean() {
    }

    protected PermissionsBean(Parcel in) {
        this.admin = in.readByte() != 0;
        this.push = in.readByte() != 0;
        this.pull = in.readByte() != 0;
    }

    public static final Parcelable.Creator<PermissionsBean> CREATOR = new Parcelable.Creator<PermissionsBean>() {
        @Override
        public PermissionsBean createFromParcel(Parcel source) {
            return new PermissionsBean(source);
        }

        @Override
        public PermissionsBean[] newArray(int size) {
            return new PermissionsBean[size];
        }
    };
}
