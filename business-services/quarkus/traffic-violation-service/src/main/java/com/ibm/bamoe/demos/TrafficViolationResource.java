package com.ibm.bamoe.demos;

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

import com.ibm.bamoe.demos.model.Driver;
import com.ibm.bamoe.demos.model.Violation;

@Path("/traffic-violation-service")
@ApplicationScoped
public class TrafficViolationResource {

    private static final Logger logger = LoggerFactory.getLogger(TrafficViolationResource.class);
    private static final double MAX_AVAILABILITY_AMOUNT = 500;

    @Inject TrafficViolationService service;

    @GET
    @Path("version")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVersion() {
        return "IBM BAMOE Version: 9.3.1-ibm-0006";
    }

    @POST()
	@Path("process-traffic-violation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response processTrafficViolation(TrafficViolationServiceRequest request) {

        try {
            return Response.ok(service.processTrafficViolation(request.getViolation(), request.getDriver())).build();
        } catch (Exception e) {

            logger.error("Exception: " + e.getMessage());
            return Response.serverError().build();
        }
    }
}
