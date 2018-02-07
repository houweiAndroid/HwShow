package com.example.houwei.hwshop.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by houwei on 2017/9/18.
 */

public class ItemBean implements Parcelable {
    private String name;




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public ItemBean() {
    }

    protected ItemBean(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<ItemBean> CREATOR = new Parcelable.Creator<ItemBean>() {
        @Override
        public ItemBean createFromParcel(Parcel source) {
            return new ItemBean(source);
        }

        @Override
        public ItemBean[] newArray(int size) {
            return new ItemBean[size];
        }
    };
}
