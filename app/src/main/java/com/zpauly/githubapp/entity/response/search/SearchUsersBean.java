package com.zpauly.githubapp.entity.response.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.zpauly.githubapp.entity.response.CodeBean;
import com.zpauly.githubapp.entity.response.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-8-10.
 */

public class SearchUsersBean implements Parcelable {
    private int total_count;
    private boolean incomplete_result;
    private List<UserBean> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public void setIncomplete_result(boolean incomplete_result) {
        this.incomplete_result = incomplete_result;
    }

    public boolean isIncomplete_result() {
        return incomplete_result;
    }

    public void setItems(List<UserBean> items) {
        this.items = items;
    }

    public List<UserBean> getItems() {
        return items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total_count);
        dest.writeByte(this.incomplete_result ? (byte) 1 : (byte) 0);
        dest.writeList(this.items);
    }

    public SearchUsersBean() {
    }

    protected SearchUsersBean(Parcel in) {
        this.total_count = in.readInt();
        this.incomplete_result = in.readByte() != 0;
        this.items = new ArrayList<UserBean>();
        in.readList(this.items, CodeBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchUsersBean> CREATOR = new Parcelable.Creator<SearchUsersBean>() {
        @Override
        public SearchUsersBean createFromParcel(Parcel source) {
            return new SearchUsersBean(source);
        }

        @Override
        public SearchUsersBean[] newArray(int size) {
            return new SearchUsersBean[size];
        }
    };
}
