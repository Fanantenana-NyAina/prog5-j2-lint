package com.project.entity;

public class Wallet {
    private String color;
    private Double size;
    private Double myBalance;
    private int Error;

    public Wallet(String color, Double size, Double myBalance) {
        this.color = color;
        this.size = size;
        this.myBalance = myBalance;
    }

    public String getColor() {
        return color;
    }

    public Double getSize() {
        return size;
    }

    public Double addVola(Double deposit) {
        var depositAmount = deposit + myBalance;
        return depositAmount;
    }

    public Double getVola(Double withdraw) {
        var WithdrawAmout = withdraw - myBalance;
        return WithdrawAmout;
    }

    public Double open() {
        return myBalance;
    }

    public String close() {
        return "It's closed";
    }

    public boolean isLost(WalletStatus walletStatus) {
        boolean isLost = walletStatus != walletStatus.WITH_ME;
        return isLost;
    }

    public Double checkVola() {
        return myBalance;
    }
}
