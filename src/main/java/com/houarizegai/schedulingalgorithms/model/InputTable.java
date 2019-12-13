package com.houarizegai.schedulingalgorithms.model;

public class InputTable {
    private double time;
    private String aFile;
    private String bFile;
    private String cFile;

    public InputTable(double time, String aFile, String bFile, String cFile) {
        this.time = time;
        this.aFile = aFile;
        this.bFile = bFile;
        this.cFile = cFile;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getAFile() {
        return aFile;
    }

    public void setAFile(String aFile) {
        this.aFile = aFile;
    }

    public String getBFile() {
        return bFile;
    }

    public void setBFile(String bFile) {
        this.bFile = bFile;
    }

    public String getCFile() {
        return cFile;
    }

    public void setCFile(String cFile) {
        this.cFile = cFile;
    }

    @Override
    public String toString() {
        return "InputTable{" +
                "time=" + time +
                ", aFile='" + aFile + '\'' +
                ", bFile='" + bFile + '\'' +
                ", cFile='" + cFile + '\'' +
                '}';
    }
}
