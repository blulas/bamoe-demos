package com.ibm.techsales.bamoe.demos.rest;

import com.ibm.techsales.bamoe.demos.model.Driver;
import com.ibm.techsales.bamoe.demos.model.Violation;

public class TrafficViolationRequest {

    private Violation violation;
    private Driver driver;

    public Violation getViolation() {
        return this.violation;
    }

    public void setViolation(Violation violation) {
        this.violation = violation;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "[traffic violation request violation=" + getViolation() + ", driver=" + getDriver() + "]";
    }
}
