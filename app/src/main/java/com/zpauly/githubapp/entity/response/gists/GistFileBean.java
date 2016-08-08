package com.zpauly.githubapp.entity.response.gists;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 16-8-5.
 */

public class GistFileBean implements Parcelable {
    /**
     * size : 932
     * raw_url : https://gist.githubusercontent.com/raw/365370/8c4d2d43d178df44f4c03a7f2ac0ff512853564e/ring.erl
     * type : text/plain
     * truncated : false
     * language : Erlang
     */

    private int size;
    private String raw_url;
    private String type;
    private boolean truncated;
    private String filename;
    private String language;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getRaw_url() {
        return raw_url;
    }

    public void setRaw_url(String raw_url) {
        this.raw_url = raw_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public GistFileBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.size);
        dest.writeString(this.raw_url);
        dest.writeString(this.type);
        dest.writeByte(this.truncated ? (byte) 1 : (byte) 0);
        dest.writeString(this.filename);
        dest.writeString(this.language);
        dest.writeString(this.content);
    }

    protected GistFileBean(Parcel in) {
        this.size = in.readInt();
        this.raw_url = in.readString();
        this.type = in.readString();
        this.truncated = in.readByte() != 0;
        this.filename = in.readString();
        this.language = in.readString();
        this.content = in.readString();
    }

    public static final Creator<GistFileBean> CREATOR = new Creator<GistFileBean>() {
        @Override
        public GistFileBean createFromParcel(Parcel source) {
            return new GistFileBean(source);
        }

        @Override
        public GistFileBean[] newArray(int size) {
            return new GistFileBean[size];
        }
    };
}
