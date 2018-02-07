package com.example.houwei.hwshop.bean.shopAndcard;

import java.util.List;

/**
 * Created by hw on 2016/10/26.
 */

public class ShopAndCardBean {
    //店的信息
//    private String shopName;
    private String sellerCustChildId;//分店ID
    private String sellerCustName;//分店名称
    private String storeSign;//招牌名称
    private String distance;//距离
    private String sellerLogoImage;//商家logo
    private String sellerImage1;//商家第一张照片
    private String sellerStarNum;//分店评价星级
    private String buyerNum;//购卡人数
    //卡的信息
    private List<CardlistBean> list;

    public String getSellerCustChildId() {
        return sellerCustChildId;
    }

    public void setSellerCustChildId(String sellerCustChildId) {
        this.sellerCustChildId = sellerCustChildId;
    }

    public String getStoreSign() {
        return storeSign;
    }

    public void setStoreSign(String storeSign) {
        this.storeSign = storeSign;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSellerLogoImage() {
        return sellerLogoImage;
    }

    public void setSellerLogoImage(String sellerLogoImage) {
        this.sellerLogoImage = sellerLogoImage;
    }

    public String getSellerCustName() {
        return sellerCustName;
    }

    public void setSellerCustName(String sellerCustName) {
        this.sellerCustName = sellerCustName;
    }

    public String getSellerImage1() {
        return sellerImage1;
    }

    public void setSellerImage1(String sellerImage1) {
        this.sellerImage1 = sellerImage1;
    }

    public String getSellerStarNum() {
        return sellerStarNum;
    }

    public void setSellerStarNum(String sellerStarNum) {
        this.sellerStarNum = sellerStarNum;
    }

    public String getBuyerNum() {
        return buyerNum;
    }

    public void setBuyerNum(String buyerNum) {
        this.buyerNum = buyerNum;
    }

    public List<CardlistBean> getList() {
        return list;
    }

    public void setList(List<CardlistBean> list) {
        this.list = list;
    }
}
