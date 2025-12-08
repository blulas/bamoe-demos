package com.ibm.bamoe.demos;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

import com.ibm.bamoe.demos.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import io.smallrye.config.SmallRyeConfig;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.ibm.bamoe.engine.adaptors.model.RuleResults;

import com.ibm.bamoe.demos.model.Deposit;
import com.ibm.bamoe.demos.model.DepositType;
import com.ibm.bamoe.demos.model.BankingCustomer;
import com.ibm.bamoe.demos.embedded.BankingDepositRules;

@WebServlet("/validate-bank-deposit")
public class BankingDepositRulesServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BankingDepositRulesServlet.class);
    private static final String BANKING_DEPOSIT_SERVICE_URL = "banking-deposit-service.url";
    private static final double MAX_AVAILABILITY_AMOUNT = 500;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Create the deposit object
        Deposit deposit = new Deposit();
        deposit.setType(DepositType.valueOf(request.getParameter("depositType")));
        deposit.setAmount(Double.parseDouble(request.getParameter("depositAmount")));
        deposit.setMaxAvailabilityAmount(MAX_AVAILABILITY_AMOUNT);

        // Add the banking customer to the deposit
        BankingCustomer customer = new BankingCustomer();
        customer.setFirstName(request.getParameter("firstName"));
        customer.setLastName(request.getParameter("lastName"));
        customer.setAccountNumber(request.getParameter("accountNumber"));
        deposit.setCustomer(customer);

        // Execute embedded or remotely
        try {
            if (request.getParameter("executionMode") == null || request.getParameter("executionMode").equalsIgnoreCase("embedded")) {
                executeEmbedded(request, response, deposit);
            } else if (request.getParameter("executionMode").equalsIgnoreCase("remote")) {
                executeRemotely(request, response, deposit);
            }
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }

    private void executeEmbedded(HttpServletRequest request, HttpServletResponse response, Deposit deposit) throws Exception {

        // Invoke the rules for the single deposit
        BankingDepositRules bankingDepositRules = new BankingDepositRules();
        RuleResults results = bankingDepositRules.processDeposit(MAX_AVAILABILITY_AMOUNT, deposit);

        // Set the rule execution results
        request.setAttribute("executionDuration", results.getExecutionDuration().getDays() + " days, " + results.getExecutionDuration().getHours() + " hours, " + results.getExecutionDuration().getMinutes() + " miniutes, " + results.getExecutionDuration().getMilliseconds() + " milliseconds");
        request.setAttribute("firedRuleCount", results.getFiredRuleCount());
        request.setAttribute("rulesFired", results.getRulesFired());

        // Get the updated deposit
        request.setAttribute("deposit", (Deposit) results.getFacts().get(0));

        // Call the response JSP
        request.getRequestDispatcher("/depositResults.jsp").forward(request, response);
    }

    private void executeRemotely(HttpServletRequest request, HttpServletResponse response, Deposit deposit) throws Exception {
        
        // Load from various property files in the classpath 
        var smallRyeConfig = ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);
        String serviceURL = smallRyeConfig.getValue(BANKING_DEPOSIT_SERVICE_URL, String.class);

        // Create the REST client interface
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
        ResteasyWebTarget target = client.target(serviceURL);
        logger.info("Invoking url=" + serviceURL);
        
        // Call the service to execute the banking deposit service
        RuleResults results = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Entity.json(deposit), RuleResults.class);

        // Close the connection
        client.close();

        // Set the rule execution results
        request.setAttribute("executionDuration", results.getExecutionDuration().getDays() + " days, " + results.getExecutionDuration().getHours() + " hours, " + results.getExecutionDuration().getMinutes() + " miniutes, " + results.getExecutionDuration().getMilliseconds() + " milliseconds");
        request.setAttribute("firedRuleCount", results.getFiredRuleCount());
        request.setAttribute("rulesFired", results.getRulesFired());

        // This all comes back as a HashMap, because we don't have the stubs
        var resultFact = (HashMap<String, Object>) results.getFacts().getFirst();
        var resultMap = (Map) results.getFacts().getFirst();
        var resultMapCustomer = (Map) resultMap.get("customer");
        var resultCustomer = new BankingCustomer(resultMapCustomer.get("accountNumber").toString(), resultMapCustomer.get("lastName").toString(), resultMapCustomer.get("firstName").toString(), Integer.parseInt(resultMapCustomer.get("age").toString()));
        var resultDeposit = new Deposit(DepositType.valueOf(resultMap.get("type").toString()), Double.parseDouble(resultMap.get("amount").toString()), Double.parseDouble(resultMap.get("maxAvailabilityAmount").toString()), resultCustomer);

        resultDeposit.setId(resultMap.get("id").toString());
        request.setAttribute("deposit", resultDeposit);

        // Call the response JSP
        request.getRequestDispatcher("/depositResults.jsp").forward(request, response);
    }
}