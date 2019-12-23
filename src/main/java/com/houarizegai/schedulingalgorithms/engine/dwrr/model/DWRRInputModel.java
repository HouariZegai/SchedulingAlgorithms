package com.houarizegai.schedulingalgorithms.engine.dwrr.model;

public class DWRRInputModel {
    private double time;
    private String aSize;
    private String bSize;
    private String cSize;

    public DWRRInputModel(double time, String aSize, String bSize, String cSize) {
        this.time = time;
        this.aSize = aSize;
        this.bSize = bSize;
        this.cSize = cSize;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getaSize() {
        return aSize;
    }

    public void setaSize(String aSize) {
        this.aSize = aSize;
    }

    public String getbSize() {
        return bSize;
    }

    public void setbSize(String bSize) {
        this.bSize = bSize;
    }

    public String getcSize() {
        return cSize;
    }

    public void setcSize(String cSize) {
        this.cSize = cSize;
    }

    @Override
    public String toString() {
        return "DWRRInputModel{" +
                "time=" + time +
                ", aSize=" + aSize +
                ", bSize=" + bSize +
                ", cSize=" + cSize +
                '}';
    }
}
