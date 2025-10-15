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

import com.ibm.techsales.bamoe.demos.model.Customer;
import com.ibm.techsales.bamoe.demos.model.rules.RuleResults;
import com.ibm.techsales.bamoe.demos.service.ClassicRuleService;

@Path("/bamoe-demos-classic-decision-service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ClassicRuleServiceResource {

    private static final Logger logger = LoggerFactory.getLogger(ClassicRuleServiceResource.class);

    @Inject ClassicRuleService service;

    @POST()
	@Path("process-customers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response processCustomers(List<Customer> customers) {

        RuleResults results = service.processCustomers(customers);
        return Response.ok(results).build();
    }
}
