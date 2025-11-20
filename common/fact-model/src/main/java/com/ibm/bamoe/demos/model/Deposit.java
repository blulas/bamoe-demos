package com.ibm.bamoe.demos.model;

public class Deposit {

    // Input
    private String id;
    private BankingCustomer customer;
    private double amount;
    private double maxAvailabilityAmount;
    private DepositType type;

    // Output
    private double amountAvailable = 0;
    private double amountOnHold = 0;
    private int numberOfDaysOnHold = 0;

    public Deposit() {
    }

    public Deposit(DepositType type, double amount, double maxAvailabilityAmount, BankingCustomer customer) {
        
        this.type = type;
        this.amount = amount;
        this.maxAvailabilityAmount = maxAvailabilityAmount;
        this.customer = customer;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BankingCustomer getCustomer() {
        return this.customer;
    }

    public void setCustomer(BankingCustomer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public DepositType getType() {
        return this.type;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    public double getMaxAvailabilityAmount() {
        return this.maxAvailabilityAmount;
    }

    public void setMaxAvailabilityAmount(double maxAvailabilityAmount) {
        this.maxAvailabilityAmount = maxAvailabilityAmount;
    }

    public double getAmountAvailable() {
        return this.amountAvailable;
    }

    public void setAmountAvailable(double amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public double getAmountOnHold() {
        return this.amountOnHold;
    }

    public void setAmountOnHold(double amountOnHold) {
        this.amountOnHold = amountOnHold;
    }

    public double getNumberOfDaysOnHold() {
        return this.numberOfDaysOnHold;
    }

    public void setNumberOfDaysOnHold(int numberOfDaysOnHold) {
        this.numberOfDaysOnHold = numberOfDaysOnHold;
    }

    @Override
    public String toString() {
        return "Deposit [id=" + id + ", type=" + type + ", amount=" + amount + ", maxAvailabilityAmount=" + maxAvailabilityAmount + ", amountAvailable=" + amountAvailable + ", amountOnHold=" + amountOnHold + ", daysOnHold=" + numberOfDaysOnHold + "]";
    }
}
