package io.mobile.finalproject.gorder;

import java.util.Date;

public class Gorder {
    private int orderNo;
    private Date orderDate;
    private int fixedPrice;
    private int discountRate;
    private String paymentMethod;
    private int gameNo;
    private String customerId;

    public Gorder(int orderNo, Date orderDate, int fixedPrice, int discountRate, String paymentMethod, int gameNo, String customerId) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.fixedPrice = fixedPrice;
        this.discountRate = discountRate;
        this.paymentMethod = paymentMethod;
        this.gameNo = gameNo;
        this.customerId = customerId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(int fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getGameNo() {
        return gameNo;
    }

    public void setGameNo(int gameNo) {
        this.gameNo = gameNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Gorder{" +
                "orderNo='" + orderNo + '\'' +
                ", orderDate=" + orderDate +
                ", fixedPrice=" + fixedPrice +
                ", discountRate=" + discountRate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", gameNo=" + gameNo +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
