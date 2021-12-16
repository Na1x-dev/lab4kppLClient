package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;

import static com.example.client.StaticFieldsAndRequests.*;

public class ApplicationCardController {
    private static final Logger log = Logger.getLogger(ApplicationCardController.class);

    @FXML
    private Label FIOLabel;

    @FXML
    private Label jobTypeLabel;

    @FXML
    private Label jobVolumeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    void onButtonRelease(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000; -fx-border-width: 0.4;");
    }

    @FXML
    void onButtonPress(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #eee; -fx-border-color: #000; -fx-border-width: 0.4;");
    }

    @FXML
    void onButtonExit(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #000; -fx-border-width: 0.4;");
    }

    @FXML
    void onButtonEnter(MouseEvent event) {
        Button someButton = (Button) event.getSource();
        someButton.setStyle("-fx-background-color: #fff; -fx-border-color: #000; -fx-border-width: 0.4;");
    }

    @FXML
    void initialize() {
        Application application = applications.get(idApplication);
        Employee employee = getResponseEmployee(application);
        jobTypeLabel.setText(application.getJobType());
        FIOLabel.setText(employee.getLastname() + " " + employee.getFirstname() + " " + employee.getPatronymic());
        jobVolumeLabel.setText(application.getJobVolume() + "м²");
        priceLabel.setText(application.getPrice() + "$");
        statusLabel.setText(application.getStatus());
        scoreLabel.setText(employee.getScore() + "");
        phoneNumberLabel.setText(employee.getPhoneNumber());
    }

    @FXML
    void toMainWindow(ActionEvent event){
            ((Node) (event.getSource())).getScene().getWindow().hide();
            log.info("окно ApplicationCard успешно закрыто");
    }

}















