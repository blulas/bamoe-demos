package com.ibm.bamoe.demos;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ibm.bamoe.engine.adaptors.model.DecisionResult;
import com.ibm.bamoe.engine.adaptors.model.DecisionModelResults;

import com.ibm.bamoe.demos.model.Driver;
import com.ibm.bamoe.demos.model.Violation;
import com.ibm.bamoe.demos.embedded.TrafficViolationDecisionModel;

@WebServlet("/process-traffic-violation")
public class TrafficViolationDecisionModelServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(TrafficViolationDecisionModelServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("--> Execution Mode=" + request.getParameter("executionMode"));

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

        logger.debug("Executing the decision model embedded...");

        // Invoke the decision model 
        TrafficViolationDecisionModel decisionModel = new TrafficViolationDecisionModel();
        DecisionModelResults results = decisionModel.processTrafficViolation(violation, driver);
        logger.info("Traffic Violation Decision Model: Results: " + results);

        // Set the rule execution results
        request.setAttribute("executionDuration", results.getExecutionDuration().getDays() + " days, " + results.getExecutionDuration().getHours() + " hours, " + results.getExecutionDuration().getMinutes() + " miniutes, " + results.getExecutionDuration().getMilliseconds() + " milliseconds");

        // Get the updated deposit
        request.setAttribute("decisionResults", results.getResults());

        // Call the response JSP
        request.getRequestDispatcher("/trafficViolationResults.jsp").forward(request, response);
    }

    private void executeRemotely(HttpServletRequest request, HttpServletResponse response, Driver driver, Violation violation) throws Exception {
        
        logger.debug("Executing decision model remotely...");

        // Set the rule execution results
        request.setAttribute("executionDuration", "");

        // Get the updated deposit

        // Call the response JSP
        request.getRequestDispatcher("/trafficViolationResults.jsp").forward(request, response);
    }
}