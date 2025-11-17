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

import com.ibm.techsales.bamoe.demos.model.Customer;

@ApplicationScoped
public class CustomerRules extends RuleEngineAdaptor {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRules.class);
    private static final String RULESET_NAME = "customer-rules";

    public RuleResults processCustomer(Integer maxAmount, Customer customer) throws Exception {
        
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(customer);
        return processCustomers(customers);
    }

    public RuleResults processCustomers(List<Customer> customers) throws Exception {

        // Convert the list of customers to a map
        Map<String, Object> facts = new HashMap<String, Object>();
        for (Customer customer : customers) {
            facts.put(customer.getId(), customer);
        }

        // Execute and return the results
        return execute(RULESET_NAME, facts);
    }
}
