package com.ibm.bamoe.demos.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.ibm.bamoe.demos.model.Deposit;
import com.ibm.bamoe.demos.embedded.BankingDepositRules;

@Path("/banking-deposit-service")
@ApplicationScoped
public class BankingDepositServiceResource {

    private static final Logger logger = LoggerFactory.getLogger(BankingDepositServiceResource.class);
    private static final double MAX_AVAILABILITY_AMOUNT = 500;

    @Inject BankingDepositRules ruleSet;

    @GET
    @Path("version")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVersion() {
        return "IBM BAMOE Version: 9.3.0-ibm-0007";
    }

    @POST()
	@Path("process-deposit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response processDeposit(Deposit deposit) {

        try {
            return Response.ok(ruleSet.processDeposit(deposit.getMaxAvailabilityAmount(), deposit)).build();
        } catch (Exception e) {

            logger.error("Exception: " + e.getMessage());
            return Response.serverError().build();
        }
    }

    @POST()
	@Path("process-deposits")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response processDeposits(List<Deposit> deposits) {

        try {
            return Response.ok(ruleSet.processDeposits(MAX_AVAILABILITY_AMOUNT, deposits)).build();
        } catch (Exception e) {

            logger.error("Exception: " + e.getMessage());
            return Response.serverError().build();
        }
    }
}
