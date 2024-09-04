package com.example.demo10904;

import java.util.Collection;

/**
 * @author 19750
 * @version 1.0
 */
public class Target {
    private int intValue;
    private String stringValue;
    private int[] intArray;
    private String[] stringArray;
    private Nested nested;
    private Collection<Integer> collection;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int[] getIntArray() {
        return intArray;
    }

    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }

    public String[] getStringArray() {
        return stringArray;
    }

    public void setStringArray(String[] stringArray) {
        this.stringArray = stringArray;
    }

    public Nested getNested() {
        return nested;
    }

    public void setNested(Nested nested) {
        this.nested = nested;
    }

    public Collection<Integer> getCollection() {
        return collection;
    }

    public void setCollection(Collection<Integer> collection) {
        this.collection = collection;
    }
}
