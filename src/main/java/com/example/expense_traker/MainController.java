package com.example.expense_traker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.time.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    //FXML variables
    @FXML
    public LineChart<Number,Number> lineChart;
    @FXML
    public NumberAxis xAxis;
    @FXML
    public NumberAxis yAxis;
    @FXML
    public AnchorPane anchorPane;

    //Variables
    public LocalDate ldt = LocalDate.now();



    //xAxis


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println(ldt);
        xAxis.setTickUnit(1);
        xAxis.setAutoRanging(false);
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    private double getHeightMainAnchorPane(){
        return anchorPane.getHeight();
    }
    private double getWidthMainAnchorPane(){
        return anchorPane.getWidth();
    }
}