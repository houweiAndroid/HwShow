package com.example.houwei.hwshop.bean.shopAndcard;



import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/27 0027.
 */

public class IndexCardlistBean implements Serializable {
    private String productId;
    private String productName;
    private String prodUnitPrice;
    private String productCon;
    private String sellerAddLong;
    private String sellerAddLati;
    private String flagProductType;
    private String distance;
    private String sellerLogoUrl;
    private String flagRefund;
    private String dueSign;
    private String productContinuedDay;
    private String probationStore;
    private String saledAmt;
    private String flagBank;
    private String flagRecharge;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProdUnitPrice() {
        return prodUnitPrice;
    }


    public String getProductCon() {
        return productCon;
    }

    public void setProductCon(String productCon) {
        this.productCon = productCon;
    }

    public String getSellerAddLong() {
        return sellerAddLong;
    }


    public String getSellerAddLati() {
        return sellerAddLati;
    }


    public String getFlagProductType() {
        return flagProductType;
    }

    public void setFlagProductType(String flagProductType) {
        this.flagProductType = flagProductType;
    }

    public String getDistance() {
        return distance;
    }


    public String getSellerLogoUrl() {
        return sellerLogoUrl;
    }


    public String getFlagRefund() {
        return flagRefund;
    }


    public String getDueSign() {
        return dueSign;
    }


    public String getProductContinuedDay() {
        return productContinuedDay;
    }


    public String getProbationStore() {
        return probationStore;
    }


    public String getSaledAmt() {
        return saledAmt;
    }


    public String getFlagBank() {
        return flagBank;
    }


    public String getFlagRecharge() {
        return flagRecharge;
    }

}
