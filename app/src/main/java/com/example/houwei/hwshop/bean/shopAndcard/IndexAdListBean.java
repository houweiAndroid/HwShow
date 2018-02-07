package com.example.houwei.hwshop.bean.shopAndcard;



import java.io.Serializable;

/**
 * Created by Eric on 2016/4/27 0027.
 */

public class IndexAdListBean implements Serializable {

    private String title;
    private String imageAddress;
    private String linkAddress;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageAddress() {
        return imageAddress;
    }


    public String getLinkAddress() {
        return linkAddress;
    }

}
