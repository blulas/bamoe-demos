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

import com.ibm.bamoe.engine.adaptors.model.DecisionModelResults;
import com.ibm.bamoe.demos.model.Driver;
import com.ibm.bamoe.demos.model.Violation;
import com.ibm.bamoe.demos.model.TrafficViolationServiceRequest;
import com.ibm.bamoe.demos.embedded.TrafficViolationDecisionModel;

@RestController
@RequestMapping("/traffic-violation-service")
public class TrafficViolationServiceResource {

    private static final Logger logger = LoggerFactory.getLogger(TrafficViolationServiceResource.class);

    private TrafficViolationDecisionModel decisionModel = new TrafficViolationDecisionModel();

    @GetMapping(path="/version", produces=MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getVersion() {
        return new ResponseEntity<String>("IBM BAMOE Version: 9.3.0-ibm-0007", HttpStatus.OK);
    }

    @PostMapping(path="/process-traffic-violation", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DecisionModelResults> processTrafficViolation(@RequestBody TrafficViolationServiceRequest request) {

        try {
            return new ResponseEntity<DecisionModelResults>(decisionModel.processTrafficViolation(request.getViolation(), request.getDriver()), HttpStatus.OK);
        } catch (Exception e) {

            logger.error("Exception: " + e.getMessage());
            return null;
        }
    }
}
