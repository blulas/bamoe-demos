package com.ibm.techsales.bamoe.demos.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.ibm.techsales.bamoe.api.model.RuleResults;
import com.ibm.techsales.bamoe.demos.model.Customer;
import com.ibm.techsales.bamoe.demos.service.CustomerRuleService;

@Path("/customer-rules-service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class RestEndpointResource {

    private static final Logger logger = LoggerFactory.getLogger(RestEndpointResource.class);

    @Inject CustomerRuleService ruleService;

    @POST()
	@Path("process-customers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response processCustomers(List<Customer> customers) {

        RuleResults results = ruleService.processCustomers(customers);
        return Response.ok(results).build();
    }
}
