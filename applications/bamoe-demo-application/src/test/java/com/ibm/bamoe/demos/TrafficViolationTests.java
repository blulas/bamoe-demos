package com.ibm.bamoe.demos;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.bamoe.engine.adaptors.model.DecisionModelResults;

import com.ibm.bamoe.demos.model.Driver;
import com.ibm.bamoe.demos.model.Violation;
import com.ibm.bamoe.demos.embedded.TrafficViolationDecisionModel;

public class TrafficViolationTests {

    private static final Logger logger = LoggerFactory.getLogger(TrafficViolationTests.class);
    
    @Test
    void testTrafficViolationDecisionModel() {

        logger.info("Testing traffic violation decision model....");

        // Prepare some test data
        Violation violation = new Violation();
        violation.setType("speed");
        violation.setSpeedLimit(100);
        violation.setActualSpeed(120);

        Driver driver = new Driver();
        driver.setName("Walter White");
        driver.setAge(55);
        driver.setCity("Albuquerque");
        driver.setState("New Mexico");
        driver.setPoints(2);

        // Invoke the decision model service
        try {

            TrafficViolationDecisionModel decisionModel = new TrafficViolationDecisionModel();
            DecisionModelResults results = decisionModel.processTrafficViolation(violation, driver);
            logger.info("Traffic Violation Decision Model: Results: " + results);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }
} 
