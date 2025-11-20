package com.project.entity;

public class Wallet {
    private String color;
    private Double size;
    private Double myBalance;

    public Wallet(String color, Double size, Double myBalance) {
        this.color = color;
        this.size = size;
        this.myBalance = myBalance;
    }

    public Double getSize() {
        return size;
    }

    public Double getMyBalance() {
        return myBalance;
    }

    public String getColor() {
        return color;
    }
}
