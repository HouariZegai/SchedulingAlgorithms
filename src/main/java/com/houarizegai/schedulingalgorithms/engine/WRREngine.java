package com.houarizegai.schedulingalgorithms.engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WRREngine {
    private List<String[]> data;
    private LinkedList<String> aList, bList, cList;
    private List<String[]> result;
    private int weightA, weightB, weightC;

    private int wrrIndex = 0, wrrSize;
    private double time = 0;

    public WRREngine(List<String[]> data, int weightA, int weightB, int weightC) {
        this.data = data;
        this.weightA = weightA;
        this.weightB = weightB;
        this.weightC = weightC;

        aList = new LinkedList<>();
        bList = new LinkedList<>();
        cList = new LinkedList<>();

        result = new ArrayList<>();

        wrrSize = data.size();
    }

    public List<String[]> getResult() {
        int counter = 0; // round roben coumter
        int iRead = -1;
        int weight; // helper variable

        while(!aList.isEmpty() || !bList.isEmpty() || !cList.isEmpty() || wrrIndex < wrrSize) {
            updateAttendLists();

            switch (iRead) {
                case 0:
                    int i = 0;
                    if(weightA > 1 && !aList.isEmpty())
                        counter = 1;

                    weight = weightA;
                    while(!aList.isEmpty() && weight > 1) {
                        result.add(new String[]{aList.poll(), String.valueOf(time += 0.5)});
                        updateAttendLists();
                        weight--;
                    }
                    break;
                case 1:
                    if(weightB > 1 && !bList.isEmpty()) {
                        counter = 2;
                    }

                    weight = weightB;
                    while(!bList.isEmpty() && weight > 1) {
                        result.add(new String[]{bList.poll(), String.valueOf(time += 0.5)});
                        updateAttendLists();
                        weight--;
                    }
                    break;
                case 2:
                    if(weightC > 1 && !cList.isEmpty()) {
                        counter = 0;
                    }

                    weight = weightC;
                    while(!cList.isEmpty() && weight > 1) {
                        result.add(new String[]{cList.poll(), String.valueOf(time += 0.5)});
                        updateAttendLists();
                        weight--;
                    }
                    break;
            }

            switch (counter) {
                case 0:
                    if(!aList.isEmpty()) {
                        result.add(new String[]{aList.poll(), String.valueOf(time + 0.5)});
                        iRead = 0;
                        time += 0.5;
                    }
                    break;
                case 1:
                    if(!bList.isEmpty()) {
                        result.add(new String[]{bList.poll(), String.valueOf(time + 0.5)});
                        iRead = 1;
                        time += 0.5;
                    }
                    break;
                case 2:
                    if(!cList.isEmpty()) {
                        result.add(new String[]{cList.poll(), String.valueOf(time + 0.5)});
                        iRead = 2;
                        time += 0.5;
                    }
                    break;
            }

            counter = ++counter % 3;
        }

        return result;
    }

    private void updateAttendLists() {
        if(wrrIndex == wrrSize)
            return;

        String[] currentPacket = data.get(wrrIndex);
        if(Double.parseDouble(currentPacket[0]) == time) {
            if(currentPacket[1] != null)
                aList.add("A");
            if(currentPacket[2] != null)
                bList.add("B");
            if(currentPacket[3] != null)
                cList.add("C");
            wrrIndex++;
        }

    }
}