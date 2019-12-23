package com.houarizegai.schedulingalgorithms.engine.dwrr.model;

import java.util.LinkedList;

public class DWRRModel {
    private final String FILE_NAME;
    private LinkedList<Double> input;
    private final double DC_SIZE;
    private double dcCredit;

    public DWRRModel(String fileName, double dcSize) {
        FILE_NAME = fileName;
        this.DC_SIZE = dcSize;

        this.dcCredit = 0d;
        this.input = new LinkedList<>();
    }

    public String getName() {
        return FILE_NAME;
    }

    public LinkedList<Double> getInput() {
        return input;
    }

    public void setInput(LinkedList<Double> input) {
        this.input = input;
    }

    public double getDcSize() {
        return DC_SIZE;
    }

    public double getDcCredit() {
        return dcCredit;
    }

    public void setDcCredit(double dcCredit) {
        this.dcCredit = dcCredit;
    }

    @Override
    public String toString() {
        return "DWRR model{" +
                "name=" + FILE_NAME +
                "input=" + input +
                ", DC=" + DC_SIZE +
                ", dcCredit=" + dcCredit +
                '}';
    }
}
