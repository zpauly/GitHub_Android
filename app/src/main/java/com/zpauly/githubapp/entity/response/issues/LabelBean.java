package com.zpauly.githubapp.entity.response.issues;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 16/9/1.
 */
public class LabelBean implements Parcelable {
    /**
     * url : https://api.github.com/repos/octocat/Hello-World/labels/bug
     * name : bug
     * color : f29513
     */

    private String url;
    private String name;
    private String color;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.name);
        dest.writeString(this.color);
    }

    public LabelBean() {
    }

    protected LabelBean(Parcel in) {
        this.url = in.readString();
        this.name = in.readString();
        this.color = in.readString();
    }

    public static final Parcelable.Creator<LabelBean> CREATOR = new Parcelable.Creator<LabelBean>() {
        @Override
        public LabelBean createFromParcel(Parcel source) {
            return new LabelBean(source);
        }

        @Override
        public LabelBean[] newArray(int size) {
            return new LabelBean[size];
        }
    };
}
