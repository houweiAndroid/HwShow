package com.example.houwei.hwshop.bean;

/**
 * Created by houwei on 2017/9/20.
 */

public class HeroBean {
        private int id;
    private String name;
    private String description;

    public HeroBean(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
