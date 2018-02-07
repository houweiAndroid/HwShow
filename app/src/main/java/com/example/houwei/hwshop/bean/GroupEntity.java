package com.example.houwei.hwshop.bean;

import com.example.houwei.hwshop.bean.shopAndcard.CardlistBean;
import com.example.houwei.hwshop.bean.shopAndcard.ShopAndCardBean;

import java.util.ArrayList;

/**
 * 组数据的实体类
 */
public class GroupEntity {

    private ShopAndCardBean header;
    private String footer;
    private ArrayList<CardlistBean> children;

    public GroupEntity(ShopAndCardBean header, String footer, ArrayList<CardlistBean> children) {
        this.header = header;
        this.footer = footer;
        this.children = children;
    }

    public ShopAndCardBean getHeader() {
        return header;
    }

    public void setHeader(ShopAndCardBean header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public ArrayList<CardlistBean> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<CardlistBean> children) {
        this.children = children;
    }
}
