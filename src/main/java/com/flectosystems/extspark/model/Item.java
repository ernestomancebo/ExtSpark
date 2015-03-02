package com.flectosystems.extspark.model;

import java.io.Serializable;

/**
 * Created by ernesto on 3/1/15.
 */
public class Item implements Serializable {

    String status;
    String item;
    String desc;
    Double weight;

    public Item() {
    }

    public Item(String status, String item, String desc, Double weight) {
        this.status = status;
        this.item = item;
        this.desc = desc;
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
