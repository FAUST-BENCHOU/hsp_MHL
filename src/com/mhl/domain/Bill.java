package com.mhl.domain;

import java.util.Date;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/6/29 14:44
 */
/*
id INT PRIMARY KEY AUTO_INCREMENT, # 自增主键
		billId VARCHAR(50) NOT NULL DEFAULT '', # 账单号可以按自己的规则生成 uuid
		menuId INT NOT NULL DEFAULT 0, # 菜品的编号，也可以使用外键
		nums INT NOT NULL DEFAULT 0, # 份数
		money DOUBLE NOT NULL DEFAULT 0, # 金额
		diningTableId INT NOT NULL DEFAULT 0, # 餐桌
		billDate DATETIME NOT NULL, # 订单日期
		state VARCHAR(24) NOT NULL DEFAULT '' # 状态 '未结账' '已经结账' '挂单' '现金' '支付宝' '坏账'
 */
public class Bill {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private double money;
    private Integer diningTableId;
    private Date billDate;
    private String state;
    private String name;
    private double price;

    public Bill() {
    }

    public Bill(Integer id, String billId, Integer menuId, Integer nums, double money, Integer diningTableId, Date billDate, String state, String name, double price) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    @Override
    public String toString() {
        return id + "\t\t" + menuId + "\t\t\t" + nums + "\t\t\t" +
                money + "\t" + diningTableId + "\t\t" + billDate + "\t\t" + state+ "\t\t" + name+ "\t\t" + price;
    }
}
