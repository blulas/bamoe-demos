package com.ibm.bamoe.demos;

import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.client.Entity.json;

import io.smallrye.config.SmallRyeConfig;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.ibm.bamoe.engine.adaptors.model.DecisionResult;
import com.ibm.bamoe.engine.adaptors.model.DecisionModelResults;

import com.ibm.bamoe.demos.model.Driver;
import com.ibm.bamoe.demos.model.Violation;
import com.ibm.bamoe.demos.model.TrafficViolationServiceRequest;
import com.ibm.bamoe.demos.embedded.TrafficViolationDecisionModel;

@WebServlet("/process-traffic-violation")
public class TrafficViolationDecisionModelServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(TrafficViolationDecisionModelServlet.class);
    private static final String TRAFFIC_VIOLATION_SERVICE_URL = "traffic-violation-service.url";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Create the violation object
        Violation violation = new Violation();
        violation.setType(request.getParameter("violationType"));
        violation.setSpeedLimit(Integer.parseInt(request.getParameter("speedLimit")));
        violation.setActualSpeed(Integer.parseInt(request.getParameter("actualSpeed")));

        // Create the driver object
        Driver driver = new Driver();
        driver.setName(request.getParameter("driverName"));
        driver.setCity(request.getParameter("city"));
        driver.setState(request.getParameter("state"));
        driver.setAge(Integer.parseInt(request.getParameter("driverAge")));
        driver.setPoints(Integer.parseInt(request.getParameter("driverPoints")));

        // Execute embedded or remotely
        try {
            if (request.getParameter("executionMode") == null || request.getParameter("executionMode").equalsIgnoreCase("embedded")) {
                executeEmbedded(request, response, driver, violation);
            } else if (request.getParameter("executionMode").equalsIgnoreCase("remote")) {
                executeRemotely(request, response, driver, violation);
            }
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }

    private void executeEmbedded(HttpServletRequest request, HttpServletResponse response, Driver driver, Violation violation) throws Exception {

        // Invoke the decision model 
        TrafficViolationDecisionModel decisionModel = new TrafficViolationDecisionModel();
        DecisionModelResults results = decisionModel.processTrafficViolation(violation, driver);

        // Set the rule execution results
        request.setAttribute("executionDuration", results.getExecutionDuration().getDays() + " days, " + results.getExecutionDuration().getHours() + " hours, " + results.getExecutionDuration().getMinutes() + " miniutes, " + results.getExecutionDuration().getMilliseconds() + " milliseconds");

        // Get the updated deposit
        request.setAttribute("decisionResults", results.getResults());

        // Call the response JSP
        request.getRequestDispatcher("/trafficViolationResults.jsp").forward(request, response);
    }

    private void executeRemotely(HttpServletRequest request, HttpServletResponse response, Driver driver, Violation violation) throws Exception {
        
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

        // Close the connection
        client.close();

        // Set the rule execution results
        request.setAttribute("executionDuration", results.getExecutionDuration().getDays() + " days, " + results.getExecutionDuration().getHours() + " hours, " + results.getExecutionDuration().getMinutes() + " miniutes, " + results.getExecutionDuration().getMilliseconds() + " milliseconds");

        // Get the updated facts
        request.setAttribute("decisionResults", results.getResults());

        // Call the response JSP
        request.getRequestDispatcher("/trafficViolationResults.jsp").forward(request, response);
    }
}