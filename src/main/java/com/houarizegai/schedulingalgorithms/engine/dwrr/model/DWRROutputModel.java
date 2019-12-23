package com.houarizegai.schedulingalgorithms.engine.dwrr.model;

public class DWRROutputModel {
    private String packetName;
    private double time;
    private double packetSize;

    public DWRROutputModel(String packetName, double time, double packetSize) {
        this.packetName = packetName;
        this.time = time;
        this.packetSize = packetSize;
    }

    public String getPacketName() {
        return packetName;
    }

    public void setPacketName(String packetName) {
        this.packetName = packetName;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(double packetSize) {
        this.packetSize = packetSize;
    }

    @Override
    public String toString() {
        return "DWRRResultModel{" +
                "packetName='" + packetName + '\'' +
                ", time=" + time +
                ", packetSize=" + packetSize +
                "}\n";
    }
}
