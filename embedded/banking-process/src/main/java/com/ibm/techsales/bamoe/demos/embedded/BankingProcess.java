package com.ibm.techsales.bamoe.demos.embedded;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import jakarta.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.bamoe.engine.adaptors.RuleEngineAdaptor;
import com.ibm.bamoe.engine.adaptors.model.RuleResults;

import com.ibm.techsales.bamoe.demos.model.Customer;
import com.ibm.techsales.bamoe.demos.model.Driver;
import com.ibm.techsales.bamoe.demos.model.Violation;

@ApplicationScoped
public class BankingProcess extends RuleEngineAdaptor {

    private static final Logger logger = LoggerFactory.getLogger(BankingProcess.class);
    private static final String RULESET_NAME = "banking-process";

    public RuleResults startProcess(List<Customer> customers, Violation violation, Driver driver) throws Exception {

        // Convert the list of customers to a map
        Map<String, Object> facts = new HashMap<String, Object>();
        for (Customer customer : customers) {
            facts.put(customer.getId(), customer);
        }

        // Add the traffic violation facts
        facts.put("Violation", violation);
        facts.put("Driver", driver);

        // Execute and return the results
        return execute(RULESET_NAME, facts);
    }
}
