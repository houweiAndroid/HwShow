package com.example.houwei.hwshop.bean.shopAndcard;



import java.io.Serializable;

/**
 * Created by hw on 2016/10/27.
 */

public class CardlistBean implements Serializable {
    private String productId;//产品ID
    private String productName;//产品名
    private String unitProduct;//卡售价
    private String unitPrice;//卡面值
    private String cardNum;//购卡张数
    private String flagProductType;//卡类型

    public String getFlagProductType() {
        return flagProductType;
    }

    public void setFlagProductType(String flagProductType) {
        this.flagProductType = flagProductType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnitProduct() {
        return unitProduct;
    }

    public void setUnitProduct(String unitProduct) {
        this.unitProduct = unitProduct;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}
