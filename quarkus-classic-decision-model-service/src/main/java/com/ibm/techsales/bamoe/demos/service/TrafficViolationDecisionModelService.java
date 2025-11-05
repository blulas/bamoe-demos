package com.ibm.techsales.bamoe.demos.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNDecisionResult;

import com.ibm.techsales.bamoe.api.decisions.ClassicDecisionModelService;
import com.ibm.techsales.bamoe.demos.model.Driver;
import com.ibm.techsales.bamoe.demos.model.Violation;

@ApplicationScoped
public class TrafficViolationDecisionModelService extends ClassicDecisionModelService {

    private static final Logger logger = LoggerFactory.getLogger(TrafficViolationDecisionModelService.class);

    // Move to application.properties file
    private static final String namespace = "https://kie.apache.org/dmn/_3F4527F1-A94B-4E30-A0FC-E02299080E1C";
    private static final String model = "Traffic Violation";
    private static final boolean enableListener = false;

    public void processTrafficViolation(Violation violation, Driver driver) {

        // Convert the list of customers to a map
        Map<String, Object> facts = new HashMap<String, Object>();
        facts.put("Violation", violation);
        facts.put("Driver", driver);

        // Execute and return the results
        DMNResult results = execute(enableListener, namespace, model, facts);
    
        // Get the results
        for (DMNDecisionResult result : results.getDecisionResults()) {  
            logger.info("Decision: '" + result.getDecisionName() + "', " + "Result: " + result.getResult());        
        }
    }
}
