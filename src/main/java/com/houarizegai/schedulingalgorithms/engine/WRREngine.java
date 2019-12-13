package com.houarizegai.schedulingalgorithms.engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WRREngine {
    private List<String[]> data;
    private LinkedList<String> aList, bList, cList;
    private List<String[]> result;
    private int weightA, weightB, weightC;

    private int spqIndex = 0, spqSize;
    private double time = 1;

    public WRREngine(List<String[]> data, int weightA, int weightB, int weightC) {
        this.data = data;
        this.weightA = weightA;
        this.weightB = weightB;
        this.weightC = weightC;

        aList = new LinkedList<>();
        bList = new LinkedList<>();
        cList = new LinkedList<>();

        result = new ArrayList<>();

        spqSize = data.size();
    }

    public List<String[]> getResult() {
        int counter = 0;
        int iRead = 0;

        while(!aList.isEmpty() || !bList.isEmpty() || !cList.isEmpty() || spqIndex < spqSize) {
            updateAttendLists();
            //System.out.println("iRead:" + iRead + ", aList:" + aList.size() + ", bList:" + bList.size() + ", cList:" + cList.size());

            switch (iRead) {
                case 1:
                    if(weightA > 1 && aList.size() < weightA) {
                       if(!aList.isEmpty())
                           counter = 1;

                       while(!aList.isEmpty()) {
                           result.add(new String[]{aList.poll(), String.valueOf(time += 0.5)});
                           updateAttendLists();
                       }
                    }
                    break;
                case 2:
                    if(weightB > 1 && bList.size() < weightB) {
                        if(!bList.isEmpty())
                            counter = 2;

                       while(!bList.isEmpty()) {
                           result.add(new String[]{bList.poll(), String.valueOf(time += 0.5)});
                           updateAttendLists();
                       }
                    }
                    break;
                case 3:
                    if(weightC > 1 && cList.size() < weightC) {
                        if(!cList.isEmpty())
                            counter = 0;

                        while(!cList.isEmpty()) {
                           result.add(new String[]{cList.poll(), String.valueOf(time += 0.5)});
                           updateAttendLists();
                        }
                    }
                    break;
            }

            switch (counter) {
                case 0:
                    if(!aList.isEmpty()) {
                        result.add(new String[]{aList.poll(), String.valueOf(time + 0.5)});
                        iRead = 1;
                        time += 0.5;
                    }
                    break;
                case 1:
                    if(!bList.isEmpty()) {
                        result.add(new String[]{bList.poll(), String.valueOf(time + 0.5)});
                        iRead = 2;
                        time += 0.5;
                    }
                    break;
                case 2:
                    if(!cList.isEmpty()) {
                        result.add(new String[]{cList.poll(), String.valueOf(time + 0.5)});
                        iRead = 3;
                        time += 0.5;
                    }
                    break;
            }

            counter = ++counter % 3;
        }

        return result;
    }

    private void updateAttendLists() {
        if(spqIndex == spqSize)
            return;

         String[] currentPacket = data.get(spqIndex);
         if(Double.parseDouble(currentPacket[0]) == time) {
             if(currentPacket[1] != null)
                 aList.add("A");
             if(currentPacket[2] != null)
                 bList.add("B");
             if(currentPacket[3] != null)
                 cList.add("C");
             spqIndex++;
         }

    }
}
