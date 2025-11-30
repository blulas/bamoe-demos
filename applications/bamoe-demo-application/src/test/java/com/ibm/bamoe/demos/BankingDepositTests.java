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

import io.smallrye.config.SmallRyeConfig;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.bamoe.engine.adaptors.model.RuleResults;

import com.ibm.bamoe.demos.model.Deposit;
import com.ibm.bamoe.demos.model.DepositType;
import com.ibm.bamoe.demos.model.BankingCustomer;
import com.ibm.bamoe.demos.embedded.BankingDepositRules;

public class BankingDepositTests {

    private static final Logger logger = LoggerFactory.getLogger(BankingDepositTests.class);
    private static final String BANKING_DEPOSIT_SERVICE_URL = "banking-deposit-service.url";
    private static final double MAX_AVAILABILITY_AMOUNT = 500;
    
    @Test
    void testEmbeddedBankDepositRules() {

        logger.info("Testing embedded bank deposit rules....");

        // Prepare some test data
        List<Deposit> deposits = new ArrayList<Deposit>();

        // Add some deposits
        deposits.add(new Deposit(DepositType.PersonalCheck,  400,     MAX_AVAILABILITY_AMOUNT, new BankingCustomer("123498464", "White", "Walter", 50)));
        deposits.add(new Deposit(DepositType.PersonalCheck,  1200,    MAX_AVAILABILITY_AMOUNT, new BankingCustomer("123498464", "White", "Walter", 50)));
        deposits.add(new Deposit(DepositType.Cash,           850,     MAX_AVAILABILITY_AMOUNT, new BankingCustomer("123498464", "White", "Walter", 50)));
        deposits.add(new Deposit(DepositType.WireTransfer,   10000,   MAX_AVAILABILITY_AMOUNT, new BankingCustomer("123498464", "White", "Walter", 50)));
        deposits.add(new Deposit(DepositType.PayrollCheck,   7250,    MAX_AVAILABILITY_AMOUNT, new BankingCustomer("123498464", "White", "Skyler", 48)));
        deposits.add(new Deposit(DepositType.OfficialCheck,  1750,    MAX_AVAILABILITY_AMOUNT, new BankingCustomer("123498464", "White", "Skyler", 48)));

        // Invoke the rule service embedded
        try {

            BankingDepositRules rules = new BankingDepositRules();
            RuleResults results = rules.processDeposits(MAX_AVAILABILITY_AMOUNT, deposits);
            logger.info("Embedded banking deposit rules: Results: " + results);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }

    @Test
    void testRemoteBankDepositRules() {

        logger.info("Testing remote bank deposit rules....");

        // Prepare some test data
        List<Deposit> deposits = new ArrayList<Deposit>();

        // Add some deposits
        deposits.add(new Deposit(DepositType.PersonalCheck,  400,    MAX_AVAILABILITY_AMOUNT, new BankingCustomer("123498464", "White", "Walter", 50)));
        deposits.add(new Deposit(DepositType.PersonalCheck,  1200,   MAX_AVAILABILITY_AMOUNT, new BankingCustomer("123498464", "White", "Walter", 50)));
        deposits.add(new Deposit(DepositType.Cash,           850,    MAX_AVAILABILITY_AMOUNT, new BankingCustomer("123498464", "White", "Walter", 50)));
        deposits.add(new Deposit(DepositType.WireTransfer,   10000,  MAX_AVAILABILITY_AMOUNT, new BankingCustomer("123498464", "White", "Walter", 50)));
        deposits.add(new Deposit(DepositType.PayrollCheck,   7250,   MAX_AVAILABILITY_AMOUNT, new BankingCustomer("123498464", "White", "Skyler", 48)));
        deposits.add(new Deposit(DepositType.OfficialCheck,  1750,   MAX_AVAILABILITY_AMOUNT, new BankingCustomer("123498464", "White", "Skyler", 48)));

        // Invoke the rule service remotely
        try {

            // Load from various property files in the classpath 
            var smallRyeConfig = ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);
            String serviceURL = smallRyeConfig.getValue(BANKING_DEPOSIT_SERVICE_URL, String.class);

            // Create the REST client interface
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(serviceURL);

            // Call the service to execute the banking deposit service
            for (int i=0; i < deposits.size(); i++) {

                Deposit deposit = (Deposit) deposits.get(i);
                RuleResults results = target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Entity.json(deposit), RuleResults.class);
                logger.info("Remote banking deposit rules: Results: " + results);
            }

            // Close the connection
            client.close();
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
    }
} 
