package com.houarizegai.schedulingalgorithms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scheduling.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        stage.setTitle("Scheduling Algorithms");
        stage.show();
    }
}
