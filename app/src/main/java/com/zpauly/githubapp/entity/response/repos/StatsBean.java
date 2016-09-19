package com.zpauly.githubapp.entity.response.repos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 16/9/19.
 */
public class StatsBean implements Parcelable {
    private int additions;
    private int deletions;
    private int total;

    public int getAdditions() {
        return additions;
    }

    public void setAdditions(int additions) {
        this.additions = additions;
    }

    public int getDeletions() {
        return deletions;
    }

    public void setDeletions(int deletions) {
        this.deletions = deletions;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.additions);
        dest.writeInt(this.deletions);
        dest.writeInt(this.total);
    }

    public StatsBean() {
    }

    protected StatsBean(Parcel in) {
        this.additions = in.readInt();
        this.deletions = in.readInt();
        this.total = in.readInt();
    }

    public static final Parcelable.Creator<StatsBean> CREATOR = new Parcelable.Creator<StatsBean>() {
        @Override
        public StatsBean createFromParcel(Parcel source) {
            return new StatsBean(source);
        }

        @Override
        public StatsBean[] newArray(int size) {
            return new StatsBean[size];
        }
    };
}
