package com.example.expense_traker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    private double x,y = 0;


    @Override
    public void start(Stage stage) throws IOException {
        //FXML LOADER
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainView.fxml"));

        //VARIABLE
        StageStyle stageStyle;
        Scene scene = new Scene(fxmlLoader.load());

        stage.setMaximized(true);

        stage.setScene(scene);
        stage.show();

        scene.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

    }

    public static void main(String[] args) throws SQLException {
        DBManagement mainDB = new DBManagement();
        /*
        mainDB.initDatabase();
        mainDB.printData();
        mainDB.addData();
        mainDB.printData();
         */

        launch();
    }
}