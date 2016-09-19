package com.zpauly.githubapp.entity.response.repos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 16/9/19.
 */
public class FileBean implements Parcelable {
    private String filename;
    private int additions;
    private int deletions;
    private int changes;
    private String status;
    private String raw_url;
    private String blob_url;
    private String patch;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

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

    public int getChanges() {
        return changes;
    }

    public void setChanges(int changes) {
        this.changes = changes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRaw_url() {
        return raw_url;
    }

    public void setRaw_url(String raw_url) {
        this.raw_url = raw_url;
    }

    public String getBlob_url() {
        return blob_url;
    }

    public void setBlob_url(String blob_url) {
        this.blob_url = blob_url;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.filename);
        dest.writeInt(this.additions);
        dest.writeInt(this.deletions);
        dest.writeInt(this.changes);
        dest.writeString(this.status);
        dest.writeString(this.raw_url);
        dest.writeString(this.blob_url);
        dest.writeString(this.patch);
    }

    public FileBean() {
    }

    protected FileBean(Parcel in) {
        this.filename = in.readString();
        this.additions = in.readInt();
        this.deletions = in.readInt();
        this.changes = in.readInt();
        this.status = in.readString();
        this.raw_url = in.readString();
        this.blob_url = in.readString();
        this.patch = in.readString();
    }

    public static final Parcelable.Creator<FileBean> CREATOR = new Parcelable.Creator<FileBean>() {
        @Override
        public FileBean createFromParcel(Parcel source) {
            return new FileBean(source);
        }

        @Override
        public FileBean[] newArray(int size) {
            return new FileBean[size];
        }
    };
}
