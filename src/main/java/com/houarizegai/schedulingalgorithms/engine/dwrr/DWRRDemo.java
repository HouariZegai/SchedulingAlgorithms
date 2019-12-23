package com.houarizegai.schedulingalgorithms.engine.dwrr;

import com.houarizegai.schedulingalgorithms.engine.dwrr.model.DWRRInputModel;
import com.houarizegai.schedulingalgorithms.engine.dwrr.model.DWRROutputModel;

import java.util.Arrays;
import java.util.List;

public class DWRRDemo {
    public static void main(String[] args) {
        List<DWRRInputModel> data = Arrays.asList(
                new DWRRInputModel(1, "1000", "400", "300"),
                new DWRRInputModel(2, "600", "300", "200"),
                new DWRRInputModel(2.5, "500", null, "400"),
                new DWRRInputModel(3, null, "400", null),
                new DWRRInputModel(3.5, "600", "300", "500"),
                new DWRRInputModel(4, "400", "600", "400"),
                new DWRRInputModel(5, "500", "500", "300"));

        DWRREngine dwrrEngine = new DWRREngine(data, 500, 500, 500);
        for(DWRROutputModel row : dwrrEngine.getResult())
            System.out.printf("%.2f | %s | %.0f\n", row.getTime(), row.getPacketName(), row.getPacketSize());

        //System.out.println(dwrrEngine.getResult());
    }
}
