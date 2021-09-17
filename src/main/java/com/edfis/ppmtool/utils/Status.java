package com.edfis.ppmtool.utils;

public enum Status {

    TO_DO("TO DO", 1), LOW("LOW", 2), HIGH("HIGH", 3),IN_PROGRESS("IN PROGRESS", 4);

    private final Integer key;
    private final String value;

    Status(String value, Integer key) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
}
