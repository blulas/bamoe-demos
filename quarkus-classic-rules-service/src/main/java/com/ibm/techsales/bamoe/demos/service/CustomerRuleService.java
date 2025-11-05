package com.ibm.techsales.bamoe.demos.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;

import com.ibm.techsales.bamoe.api.rules.ClassicRuleService;
import com.ibm.techsales.bamoe.api.model.RuleResults;
import com.ibm.techsales.bamoe.demos.model.Customer;

@ApplicationScoped
public class CustomerRuleService extends ClassicRuleService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRuleService.class);

    public RuleResults processCustomers(List<Customer> customers) {

        logger.debug("Processing customers...");

        // Convert the list of customers to a map
        Map<String, Object> facts = new HashMap<String, Object>();
        for (Customer customer : customers) {
            facts.put(customer.getId(), customer);
        }
        
        // Execute and return the results
        return execute(false, facts);
    }
}
