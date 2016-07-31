package com.zpauly.githubapp.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by zpauly on 16-6-12.
 */
public class UserModel extends DataSupport implements Parcelable {
    private String login;
    private String avatar_url;
    private String type;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private boolean hirable;
    private String bio;
    private int public_repos;
    private int public_gists;
    private int followers;
    private int following;
    private String created_at;
    private String updated_at;
    private boolean user;

    public void setUser(boolean user) {
        this.user = user;
    }

    public boolean isUser() {
        return user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHirable() {
        return hirable;
    }

    public void setHirable(boolean hirable) {
        this.hirable = hirable;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public int getPublic_gists() {
        return public_gists;
    }

    public void setPublic_gists(int public_gists) {
        this.public_gists = public_gists;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeString(this.avatar_url);
        dest.writeString(this.type);
        dest.writeString(this.name);
        dest.writeString(this.company);
        dest.writeString(this.blog);
        dest.writeString(this.location);
        dest.writeString(this.email);
        dest.writeByte(this.hirable ? (byte) 1 : (byte) 0);
        dest.writeString(this.bio);
        dest.writeInt(this.public_repos);
        dest.writeInt(this.public_gists);
        dest.writeInt(this.followers);
        dest.writeInt(this.following);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
        dest.writeByte(this.user ? (byte) 1 : (byte) 0);
    }

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        this.login = in.readString();
        this.avatar_url = in.readString();
        this.type = in.readString();
        this.name = in.readString();
        this.company = in.readString();
        this.blog = in.readString();
        this.location = in.readString();
        this.email = in.readString();
        this.hirable = in.readByte() != 0;
        this.bio = in.readString();
        this.public_repos = in.readInt();
        this.public_gists = in.readInt();
        this.followers = in.readInt();
        this.following = in.readInt();
        this.created_at = in.readString();
        this.updated_at = in.readString();
        this.user = in.readByte() != 0;
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
