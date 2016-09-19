package com.zpauly.githubapp.entity.response.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-8-9.
 */

public class SearchReposBean implements Parcelable {
    private int total_count;
    private boolean incomplete_result;
    private List<RepositoriesBean> items;

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setIncomplete_result(boolean incomplete_result) {
        this.incomplete_result = incomplete_result;
    }

    public boolean isIncomplete_result() {
        return incomplete_result;
    }

    public void setItems(List<RepositoriesBean> items) {
        this.items = items;
    }

    public List<RepositoriesBean> getItems() {
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

    public SearchReposBean() {
    }

    protected SearchReposBean(Parcel in) {
        this.total_count = in.readInt();
        this.incomplete_result = in.readByte() != 0;
        this.items = new ArrayList<RepositoriesBean>();
        in.readList(this.items, RepositoriesBean.class.getClassLoader());
    }

    public static final Creator<SearchReposBean> CREATOR = new Creator<SearchReposBean>() {
        @Override
        public SearchReposBean createFromParcel(Parcel source) {
            return new SearchReposBean(source);
        }

        @Override
        public SearchReposBean[] newArray(int size) {
            return new SearchReposBean[size];
        }
    };
}
