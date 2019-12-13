package com.houarizegai.schedulingalgorithms.model;

public class OutputTable {
    private double time;
    private String packet;

    public OutputTable() {
    }

    public OutputTable(double time, String packet) {
        this.time = time;
        this.packet = packet;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getPacket() {
        return packet;
    }

    public void setPacket(String packet) {
        this.packet = packet;
    }

    @Override
    public String toString() {
        return "OutputTable{" +
                "time=" + time +
                ", packet='" + packet + '\'' +
                '}';
    }
}
