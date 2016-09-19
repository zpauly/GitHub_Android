package com.zpauly.githubapp.entity.response.repos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 16/9/19.
 */
public class VerificationBean implements Parcelable {
    private boolean verified;
    private String reason;
    private String signature;
    private String payload;

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.verified ? (byte) 1 : (byte) 0);
        dest.writeString(this.reason);
        dest.writeString(this.signature);
        dest.writeString(this.payload);
    }

    public VerificationBean() {
    }

    protected VerificationBean(Parcel in) {
        this.verified = in.readByte() != 0;
        this.reason = in.readString();
        this.signature = in.readString();
        this.payload = in.readString();
    }

    public static final Parcelable.Creator<VerificationBean> CREATOR = new Parcelable.Creator<VerificationBean>() {
        @Override
        public VerificationBean createFromParcel(Parcel source) {
            return new VerificationBean(source);
        }

        @Override
        public VerificationBean[] newArray(int size) {
            return new VerificationBean[size];
        }
    };
}
