package com.zc.takeout.bean;

import java.io.Serializable;

public class Dish implements Serializable {

    private Integer _id;

    private String name;

    private double price;

    private String image;

    private String category;

    private String description;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Dish() {
    }

    public Dish(Integer _id, String name, double price, String image, String category, String description) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
