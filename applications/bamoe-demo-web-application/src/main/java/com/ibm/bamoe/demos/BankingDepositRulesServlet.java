package com.ibm.bamoe.demos;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ibm.bamoe.engine.adaptors.model.RuleResults;

import com.ibm.bamoe.demos.model.Deposit;
import com.ibm.bamoe.demos.model.DepositType;
import com.ibm.bamoe.demos.model.BankingCustomer;
import com.ibm.bamoe.demos.embedded.BankingDepositRules;

@WebServlet("/validate-bank-deposit")
public class BankingDepositRulesServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BankingDepositRulesServlet.class);
    private static final double MAX_AVAILABILITY_AMOUNT = 500;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("--> Execution Mode=" + request.getParameter("executionMode"));

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

        logger.debug("Executing rules embedded...");

        // Invoke the rules for the single deposit
        BankingDepositRules bankingDepositRules = new BankingDepositRules();
        RuleResults results = bankingDepositRules.processDeposit(MAX_AVAILABILITY_AMOUNT, deposit);
        logger.info("Banking Deposit Rules: Results: " + results);

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
        
        logger.debug("Executing rules remotely...");

        request.setAttribute("startedOn", "");
        request.setAttribute("completedOn", "");
        request.setAttribute("firedRuleCount", "");
        request.setAttribute("executionDuration", "");
        request.setAttribute("rulesFired", new ArrayList<String>());

        // Get the updated deposit
        request.setAttribute("deposit", deposit);

        // Call the response JSP
        request.getRequestDispatcher("/depositResults.jsp").forward(request, response);
    }
}