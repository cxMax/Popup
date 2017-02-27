package com.cxmax.popup.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @describe : coupon has three state {grasp,loading,received}
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/2/27.
 */

public class CouponItem implements Parcelable, Serializable {

    public String title;
    public String content;

    static final int GRASP = 0;
    static final int LOADING = 1;
    static final int RECEIVED = 2;

    @CouponState
    public int state = GRASP;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(value = {GRASP, LOADING, RECEIVED})
    @interface CouponState {
    }

    public static final Parcelable.Creator<CouponItem> CREATOR = new Parcelable.Creator<CouponItem>() {
        @Override
        public CouponItem createFromParcel(Parcel source) {
            return new CouponItem(source);
        }

        @Override
        public CouponItem[] newArray(int size) {
            return new CouponItem[size];
        }
    };

    public CouponItem() {

    }

    public CouponItem(String title , String content) {
        this.title = title;
        this.content = content;
    }

    public CouponItem(Parcel parcel) {
        readFromParcel(parcel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(state);
    }

    public void readFromParcel(Parcel src) {
        //noinspection WrongConstant
        title = src.readString();
        content = src.readString();
        state = src.readInt();
    }
}
