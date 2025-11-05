package com.ibm.techsales.bamoe.demos.model;

public class Driver {

    private String name;
    private int age;
    private String state;
    private String city;
    private int points;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "[driver name=" + getName() + ", age=" + getAge() + ", city=" + getCity() + ", state=" + getState() + ", points=" + getPoints() + "]";
    }
}