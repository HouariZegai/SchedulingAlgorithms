package com.houarizegai.schedulingalgorithms.engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SPQEngine {
    private int spqIndex = 0, spqSize;
    private double time = 0;

    private List<String[]> data;
    private LinkedList<String> aList, bList, cList;
    private List<String[]> result;

    public SPQEngine(List<String[]> data) {
        this.data = data;
        aList = new LinkedList<>();
        bList = new LinkedList<>();
        cList = new LinkedList<>();

        result = new ArrayList<>();

        spqSize = data.size();
    }

    public List<String[]> getResult() {
        while(!aList.isEmpty() || !bList.isEmpty() || !cList.isEmpty() || spqIndex < spqSize) {
            updateAttendLists();
            if(!aList.isEmpty())
                result.add(new String[]{aList.poll(), String.valueOf(time + 0.5)});
            else if(!bList.isEmpty())
                result.add(new String[]{bList.poll(), String.valueOf(time + 0.5)});
            else if(!cList.isEmpty())
                result.add(new String[]{cList.poll(), String.valueOf(time + 0.5)});

            time += 0.5;
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
