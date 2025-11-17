package com.ibm.techsales.bamoe.demos.embedded;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import jakarta.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.bamoe.engine.adaptors.RuleEngineAdaptor;
import com.ibm.bamoe.engine.adaptors.model.RuleResults;

import com.ibm.techsales.bamoe.demos.model.Deposit;

@ApplicationScoped
public class BankingDepositRules extends RuleEngineAdaptor {

    private static final Logger logger = LoggerFactory.getLogger(BankingDepositRules.class);
    private static final String RULESET_NAME = "banking-deposit-rules";

    public RuleResults processDeposit(Integer maxAmount, Deposit deposit) throws Exception {
        
        List<Deposit> deposits = new ArrayList<Deposit>();
        deposits.add(deposit);
        return processDeposits(maxAmount, deposits);
    }

    public RuleResults processDeposits(Integer maxAmount, List<Deposit> deposits) throws Exception {

        // Add the facts 
        Map<String, Object> facts = new HashMap<String, Object>();
        facts.put("maxAmount", maxAmount);

        for (Deposit deposit : deposits) {
            facts.put(deposit.getId(), deposit);
        }

        // Execute and return the results
        return execute(RULESET_NAME, facts);
    }
}
