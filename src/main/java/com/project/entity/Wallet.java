package com.project.entity;

public class Wallet {
    private String color;
    private Size size;
    private Double myBalance;
    private String tenyGasy;
    //private String ErrorVar;
    //private String error_var;

    public Wallet(String color, Size size, Double myBalance) {
        this.color = color;
        this.size = size;
        this.myBalance = myBalance;
    }

    public String getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public Double addVola(Double deposit) {
        var depositAmount = deposit + myBalance;
        return depositAmount;
    }

    public Double getVola(Double withdraw) {
        var withdrawAmount = withdraw - myBalance;
        return withdrawAmount;
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
