package com.ibm.techsales.bamoe.demos.embedded.rules;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

import com.ibm.techsales.bamoe.demos.model.Deposit;
import com.ibm.techsales.bamoe.demos.model.AllAmounts;

public class BankingDepositUnit implements RuleUnitData {

    private int maxAmount;
    private DataStore<Deposit> deposits;
    private DataStore<AllAmounts> allAmounts;

    public BankingDepositUnit() {
        this(DataSource.createStore(), DataSource.createStore());
    }

    public BankingDepositUnit(DataStore<Deposit> deposits, DataStore<AllAmounts> allAmounts) {

        this.deposits = deposits;
        this.allAmounts = allAmounts;
    }

    public DataStore<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(DataStore<Deposit> deposits) {
        this.deposits = deposits;
    }

    public DataStore<AllAmounts> getAllAmounts() {
        return allAmounts;
    }

    public void setAllAmounts(DataStore<AllAmounts> allAmounts) {
        this.allAmounts = allAmounts;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }
}
