package com.mhl.domain;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/6/27 22:14
 */
public class DinningTable {
    private Integer id;
    private String state;
    private String orderName;
    private String orderTel;

    public DinningTable() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }
}
