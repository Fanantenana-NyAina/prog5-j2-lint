package com.project.entity;
import java.util.*;

public class Wallet {
    private String color;
    private Double size;
    private Double myBalance;

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

    public Double getVola(Double retrieveAmount) {
        return myBalance - retrieveAmount;
    }

    public Double addVola(Double addAmount) {
        return myBalance + addAmount;
    }

    public Double open() {
        return myBalance;
    }

    public String close() {
        return "It's closed";
    }

    public Boolean isLost(WalletStatus walletStatus) {
        return walletStatus != WalletStatus.WITH_ME;
    }

    public Double checkVola() {
        return myBalance;
    }
}
