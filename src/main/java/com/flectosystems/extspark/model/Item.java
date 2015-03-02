package com.flectosystems.extspark.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ernesto on 3/1/15.
 */
@Entity
@Table(
        name = "item",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "ITEM")
        }
)
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM", unique = true, nullable = false)
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Column(name = "STATUS", nullable = false, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "DESC", nullable = false, length = 30)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Column(name = "WEIGHT", nullable = false, precision = 2)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
