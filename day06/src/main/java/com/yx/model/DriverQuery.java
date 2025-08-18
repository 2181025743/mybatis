package com.yx.model;

public class DriverQuery extends DriverInfo {
    private Person person;

    @Override
    public String toString() {
        return "DriverQuery{" +
                "person=" + person +
                "} " + super.toString();
    }
}
