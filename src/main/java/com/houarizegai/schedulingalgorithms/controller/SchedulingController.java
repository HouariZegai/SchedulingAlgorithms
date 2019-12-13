package com.houarizegai.schedulingalgorithms.controller;

import com.houarizegai.schedulingalgorithms.engine.SPQEngine;
import com.houarizegai.schedulingalgorithms.model.InputTable;
import com.houarizegai.schedulingalgorithms.model.OutputTable;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SchedulingController implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private JFXTextField fieldInputTime, fieldInputFileA, fieldInputFileB, fieldInputFileC;

    @FXML
    private TableView<InputTable> tableInput;

    @FXML
    private TableColumn<InputTable, ?> colInTime, colInA, colInB, colInC;

    @FXML
    private JFXRadioButton radioSpq, radioWrr;

    @FXML
    private VBox boxWrrWeightContainer;
    @FXML
    private JFXTextField fieldAWeight, fieldBWeight, fieldCWeight;

    @FXML
    private TableView<OutputTable> tableOutput;
    @FXML
    private TableColumn<InputTable, ?> colOutTime, colOutPacket;

    private JFXSnackbar toastErrorMsg;

    private ObservableList<InputTable> inputTableData;
    private ObservableList<OutputTable> outputTableData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        radioWrr.selectedProperty().addListener(e-> {
            if(radioWrr.isSelected()) {
                boxWrrWeightContainer.setDisable(false);
            } else {
                boxWrrWeightContainer.setDisable(true);
                fieldAWeight.setText(null);
                fieldBWeight.setText(null);
                fieldCWeight.setText(null);
            }
        });

        toastErrorMsg = new JFXSnackbar(root);

        initTables();
    }

    private void initTables() {
        /* Init input table */
        for(TableColumn column: tableInput.getColumns())
            column.setStyle("-fx-alignment: center");

        colInTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colInA.setCellValueFactory(new PropertyValueFactory<>("aFile"));
        colInB.setCellValueFactory(new PropertyValueFactory<>("bFile"));
        colInC.setCellValueFactory(new PropertyValueFactory<>("cFile"));

        inputTableData = FXCollections.observableArrayList();
        tableInput.setItems(inputTableData);

        /* Init output table */
        for(TableColumn column: tableOutput.getColumns())
            column.setStyle("-fx-alignment: center");

        colOutTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colOutPacket.setCellValueFactory(new PropertyValueFactory<>("packet"));
        outputTableData = FXCollections.observableArrayList();
        tableOutput.setItems(outputTableData);
    }

    @FXML
    public void onAddToInput() {
        if(fieldInputTime.getText() == null || fieldInputTime.getText().isEmpty()) {
            toastErrorMsg.show("Please type the input time before adding new input line!", 1500);
            return;
        }

        inputTableData.add(new InputTable(Double.parseDouble(fieldInputTime.getText()), fieldInputFileA.getText(), fieldInputFileB.getText(), fieldInputFileC.getText()));

        fieldInputTime.setText(null);
        fieldInputFileA.setText(null);
        fieldInputFileB.setText(null);
        fieldInputFileC.setText(null);
    }

    @FXML
    public void onStart() {
        if(radioSpq.isSelected()) {
            if(!inputTableData.isEmpty()) {
                List<String[]> outputData = new ArrayList<>();
                for(InputTable row : inputTableData) {
                    outputData.add(new String[]{String.valueOf(row.getTime()), row.getAFile(), row.getBFile(), row.getCFile()});
                }
                SPQEngine spqEngine = new SPQEngine(outputData);

                List<String[]> result = spqEngine.getResult();
                outputTableData.clear();
                for(String[] row : result) {
                    outputTableData.add(new OutputTable(Double.parseDouble(row[1]), row[0]));
                }

            } else
                toastErrorMsg.show("Please insert at least one element!", 1500);

        } else if(radioWrr.isSelected()) {

        }
    }
}
