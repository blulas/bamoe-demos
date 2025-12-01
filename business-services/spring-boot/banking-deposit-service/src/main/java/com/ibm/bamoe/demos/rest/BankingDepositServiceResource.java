package com.ibm.bamoe.demos.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bamoe.engine.adaptors.model.RuleResults;
import com.ibm.bamoe.demos.model.Deposit;
import com.ibm.bamoe.demos.embedded.BankingDepositRules;

@RestController
@RequestMapping("/banking-deposit-service")
public class BankingDepositServiceResource {

    private static final Logger logger = LoggerFactory.getLogger(BankingDepositServiceResource.class);
    private static final double MAX_AVAILABILITY_AMOUNT = 500;

    private BankingDepositRules ruleSet = new BankingDepositRules();

    @GetMapping(path="/version", produces=MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getVersion() {
        return new ResponseEntity<String>("IBM BAMOE Version: 9.3.0-ibm-0007", HttpStatus.OK);
    }

    @PostMapping(path="/process-deposit", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RuleResults> processDeposit(@RequestBody Deposit deposit) {

        try {
            return new ResponseEntity<RuleResults>(ruleSet.processDeposit(deposit.getMaxAvailabilityAmount(), deposit), HttpStatus.OK);
        } catch (Exception e) {

            logger.error("Exception: " + e.getMessage());
            return null;
        }
    }

    @PostMapping(path="/process-deposits", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RuleResults> processDeposits(@RequestBody List<Deposit> deposits) {

        try {
            return new ResponseEntity<RuleResults>(ruleSet.processDeposits(MAX_AVAILABILITY_AMOUNT, deposits), HttpStatus.OK);
        } catch (Exception e) {

            logger.error("Exception: " + e.getMessage());
            return null;
        }
    }
}
