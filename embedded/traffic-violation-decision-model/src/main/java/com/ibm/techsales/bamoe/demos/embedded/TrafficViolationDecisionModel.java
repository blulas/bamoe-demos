package com.ibm.techsales.bamoe.demos.embedded;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import jakarta.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.bamoe.engine.adaptors.model.DecisionModelResults;
import com.ibm.bamoe.engine.adaptors.DecisionModelAdaptor;

import com.ibm.techsales.bamoe.demos.model.Driver;
import com.ibm.techsales.bamoe.demos.model.Violation;

@ApplicationScoped
public class TrafficViolationDecisionModel extends DecisionModelAdaptor {

    private static final Logger logger = LoggerFactory.getLogger(TrafficViolationDecisionModel.class);
    private static final String DECISION_MODEL_NAME = "traffic-violation-decision-model";

    public DecisionModelResults processTrafficViolation(Violation violation, Driver driver) throws Exception {

        // Convert the list of customers to a map
        Map<String, Object> facts = new HashMap<String, Object>();
        facts.put("Violation", violation);
        facts.put("Driver", driver);

        // Execute and return the results
        return execute(DECISION_MODEL_NAME, facts);
    }
}
