package com.houarizegai.schedulingalgorithms.engine;

import java.util.ArrayList;
import java.util.List;

public class SchedulingDemo {
    // For testing algorithms in console
    public static void main(String[] args) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"1", "A", "B", "C"});
        data.add(new String[]{"1.5", "A", "B", null});

        List<String[]> data2 = new ArrayList<>();
        data2.add(new String[]{"1", "A", null, "C"});
        data2.add(new String[]{"2", null, "B", "C"});
        data2.add(new String[]{"3", "A", "B", null});
        data2.add(new String[]{"3.5", "A", null, "C"});
        data2.add(new String[]{"4", null, "B", "C"});
        data2.add(new String[]{"5", "A", "B", null});
        data2.add(new String[]{"5.5", "A", null, "C"});

        //SPQEngine spqEngine = new SPQEngine(data);
        //List<String[]> result = spqEngine.getResult();

        WRREngine wrrEngine = new WRREngine(data2, 1, 1, 2);
        List<String[]> result = wrrEngine.getResult();

        for(String[] row : result) {
            System.out.println(row[0] + " : " + row[1]);
        }
    }
}
