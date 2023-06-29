package com.mhl.domain;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/6/29 12:39
 */
public class Menu {
    private Integer id;
    private String name;
    private String type;
    private double price;

    public Menu() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id+"\t\t\t"+name+"\t\t"+type+"\t\t"+price;
    }
}
