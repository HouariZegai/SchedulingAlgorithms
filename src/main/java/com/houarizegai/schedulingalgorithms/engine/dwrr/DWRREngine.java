package com.houarizegai.schedulingalgorithms.engine.dwrr;

import com.houarizegai.schedulingalgorithms.engine.dwrr.model.DWRRInputModel;
import com.houarizegai.schedulingalgorithms.engine.dwrr.model.DWRRModel;
import com.houarizegai.schedulingalgorithms.engine.dwrr.model.DWRROutputModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;

public class DWRREngine {
    private LinkedList<DWRRInputModel> data;
    private DWRRModel aFile, bFile, cFile;
    
    private List<DWRROutputModel> result;
    int roundRobenCounter = 0;
    private double time;

    public DWRREngine(List<DWRRInputModel> data, int aDCSize, int bDCSize, int cDCSize) {
        this.data = new LinkedList<>(data);
        aFile = new DWRRModel("A", aDCSize);
        bFile = new DWRRModel("B", bDCSize);
        cFile = new DWRRModel("C", cDCSize);

        result = new ArrayList<>();

        this.time = this.data.getFirst().getTime();
    }

    public List<DWRROutputModel> getResult() {
        while(!data.isEmpty() || !aFile.getInput().isEmpty() || !bFile.getInput().isEmpty() || !cFile.getInput().isEmpty()) {
            updateAttendLists();

            switch (roundRobenCounter) {
                case 0:
                    if(aFile.getInput().isEmpty()) {
                        aFile.setDcCredit(0);
                    } else {
                        aFile.setDcCredit(aFile.getDcCredit() + aFile.getDcSize());
                        doJobForPacket(aFile);
                    }
                    break;
                case 1:
                    if(bFile.getInput().isEmpty()) {
                        bFile.setDcCredit(0);
                    } else {
                        bFile.setDcCredit(bFile.getDcCredit() + bFile.getDcSize());
                        doJobForPacket(bFile);
                    }
                    break;
                case 2:
                    if(cFile.getInput().isEmpty()) {
                        cFile.setDcCredit(0);
                    } else {
                        cFile.setDcCredit(cFile.getDcCredit() + cFile.getDcSize());
                        doJobForPacket(cFile);
                    }
                    break;
            }

            roundRobenCounter = ++roundRobenCounter % 3;
        }

        return result;
    }

    private void doJobForPacket(DWRRModel file) {
        while(!file.getInput().isEmpty() && file.getInput().getFirst() <= file.getDcCredit()) {
            double packet = file.getInput().poll();
            file.setDcCredit(file.getDcCredit() - packet);
            result.add(new DWRROutputModel(file.getName(), time += packet / 1000, packet));
        }
    }

    private void updateAttendLists() {
        while(!data.isEmpty() && data.getFirst().getTime() <= time) {
            DWRRInputModel currentPacket = data.poll();
            if(currentPacket.getaSize() != null)
                aFile.getInput().add(Double.parseDouble(currentPacket.getaSize()));
            if(currentPacket.getbSize() != null)
                bFile.getInput().add(Double.parseDouble(currentPacket.getbSize()));
            if(currentPacket.getcSize() != null)
                cFile.getInput().add(Double.parseDouble(currentPacket.getcSize()));
        }

    }
}