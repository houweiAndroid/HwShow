package com.example.houwei.hwshop.bean.shopAndcard;



import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hw on 2016/11/4.
 */

public class HandleBean2 implements Serializable {

    private boolean status;
    private int error_code;
    private String msgState;
    private ArrayList<IndexAdListBean> indexAdList;
    private ArrayList<IndexAdListBean> indexLoadImageList;
    private ArrayList<IndexCardlistBean> indexCardlist;
    //    private ArrayList<CarouseAdListBean> carouseAdList;
    private ArrayList<IndexShopListBean> indexStorelist;

    public ArrayList<IndexShopListBean> getIndexStorelist() {
        return indexStorelist;
    }

    public void setIndexStorelist(ArrayList<IndexShopListBean> indexStorelist) {
        this.indexStorelist = indexStorelist;
    }

    public void setMsgState(String msgState) {
        this.msgState = msgState;
    }

    public void setIndexAdList(ArrayList<IndexAdListBean> indexAdList) {
        this.indexAdList = indexAdList;
    }

    public void setIndexLoadImageList(ArrayList<IndexAdListBean> indexLoadImageList) {
        this.indexLoadImageList = indexLoadImageList;
    }

    public void setIndexCardlist(ArrayList<IndexCardlistBean> indexCardlist) {
        this.indexCardlist = indexCardlist;
    }





    public String getMsgState() {
        return msgState;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ArrayList<IndexAdListBean> getIndexAdList() {
        return indexAdList;
    }


    public ArrayList<IndexAdListBean> getIndexLoadImageList() {
        return indexLoadImageList;
    }


    public ArrayList<IndexCardlistBean> getIndexCardlist() {
        return indexCardlist;
    }


//    public ArrayList<CarouseAdListBean> getCarouseAdList() {
//        return carouseAdList;
//    }
//
//    public void setCarouseAdList(ArrayList<CarouseAdListBean> carouseAdList) {
//        this.carouseAdList = carouseAdList;
//    }
}
