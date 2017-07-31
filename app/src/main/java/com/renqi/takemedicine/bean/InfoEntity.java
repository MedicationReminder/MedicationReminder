package com.renqi.takemedicine.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Xu Wei on 2017/7/30.
 */

public class InfoEntity implements Parcelable {
    private String title;
    private String showNumber;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(String showNumber) {
        this.showNumber = showNumber;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.showNumber);
    }

    public InfoEntity() {}

    public InfoEntity(String title, String showNumber) {
        this.title = title;
        this.showNumber = showNumber;
    }

    protected InfoEntity(Parcel in) {
        this.title = in.readString();
        this.showNumber = in.readString();
    }

    public static final Parcelable.Creator<InfoEntity> CREATOR = new Parcelable.Creator<InfoEntity>() {
        @Override
        public InfoEntity createFromParcel(Parcel source) {return new InfoEntity(source);}

        @Override
        public InfoEntity[] newArray(int size) {return new InfoEntity[size];}
    };
}
