package com.ibm.bamoe.demos.model;

import java.util.Date;

public class Violation {

    private String code;
    private Date date;
    private String type;
    private int speedLimit;
    private int actualSpeed;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpeedLimit() {
        return this.speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public int getActualSpeed() {
        return this.actualSpeed;
    }

    public void setActualSpeed(int actualSpeed) {
        this.actualSpeed = actualSpeed;
    }

    @Override
    public String toString() {
        return "[violation code=" + getCode() + ", date=" + getDate() + ", type=" + getType() + "]";
    }
}
