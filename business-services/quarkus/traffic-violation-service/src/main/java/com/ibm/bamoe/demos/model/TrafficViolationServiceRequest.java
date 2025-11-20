package com.ibm.bamoe.demos.model;

import com.ibm.bamoe.demos.model.Driver;
import com.ibm.bamoe.demos.model.Violation;

public class TrafficViolationServiceRequest {

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
