package com.ibm.techsales.bamoe.demos.model;

public class Fine {

    private int amount;
    private int points;

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "[fine amount=" + getAmount() + ", points=" + getPoints() + "]";
    }
}
