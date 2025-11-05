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

import com.ibm.techsales.bamoe.demos.service.TrafficViolationDecisionModelService;
import com.ibm.techsales.bamoe.demos.rest.TrafficViolationRequest;
import com.ibm.techsales.bamoe.demos.model.Driver;
import com.ibm.techsales.bamoe.demos.model.Violation;

@Path("/traffic-violation-decision-model-service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class RestEndpointResource {

    private static final Logger logger = LoggerFactory.getLogger(RestEndpointResource.class);

    @Inject TrafficViolationDecisionModelService decisionModelService;

    @POST()
	@Path("process-traffic-violation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response processTrafficViolation(TrafficViolationRequest request) {

        decisionModelService.processTrafficViolation(request.getViolation(), request.getDriver());
        return Response.ok().build();
    }
}
