package com.ibm.bamoe.demos;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.client.Entity.json;

import io.smallrye.config.SmallRyeConfig;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.bamoe.engine.adaptors.model.DecisionModelResults;

import com.ibm.bamoe.demos.model.Driver;
import com.ibm.bamoe.demos.model.Violation;
import com.ibm.bamoe.demos.model.TrafficViolationServiceRequest;
import com.ibm.bamoe.demos.embedded.TrafficViolationDecisionModel;

public class TrafficViolationTests {

    private static final Logger logger = LoggerFactory.getLogger(TrafficViolationTests.class);
    private static final String TRAFFIC_VIOLATION_SERVICE_URL = "traffic-violation-service.url";
    
    @Test
    void testEmbeddedTrafficViolationDecisionModel() {

        logger.info("Testing embedded traffic violation decision model....");

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
            logger.info("Embedded traffic violation decision model: Results: " + results);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }

    @Test
    void testRemoteTrafficViolationDecisionModel() {

        logger.info("Testing remote traffic violation decision model....");

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

            // Load from various property files in the classpath 
            var smallRyeConfig = ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);
            String serviceURL = smallRyeConfig.getValue(TRAFFIC_VIOLATION_SERVICE_URL, String.class);

            // Create the REST client interface
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(serviceURL);

            // Call the service to execute the traffic violation service
            TrafficViolationServiceRequest trafficViolationRequest = new TrafficViolationServiceRequest();
            trafficViolationRequest.setViolation(violation);
            trafficViolationRequest.setDriver(driver);
            DecisionModelResults results = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(json(trafficViolationRequest), DecisionModelResults.class);
            logger.info("Remote traffic violation decision model: Results: " + results);

            // Close the connection
            client.close();
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }
} 
