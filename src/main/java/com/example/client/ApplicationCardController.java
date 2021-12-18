package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.regex.Pattern;

import static com.example.client.StaticFieldsAndRequests.*;

public class ApplicationCardController {
    private static final Logger log = Logger.getLogger(ApplicationCardController.class);
    private Application application;
    private Employee employee;

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
    private TextField scoreField;

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
        application = applications.get(idApplication);
        employee = getResponseEmployee(application);
        initScoreField();
        initLabels();
    }

    @FXML
    void toMainWindow(ActionEvent event) {
        if (application.getStatus().equals("Выполнен") && isConnected) {
            employee = calculateEmployeeScore();
            putResponseApplication(application);
            putResponseEmployee(employee);
        }
        ((Node) (event.getSource())).getScene().getWindow().hide();
        log.info("окно ApplicationCard успешно закрыто");
    }

    void initScoreField() {
        Pattern p = Pattern.compile("(\\d{0,2})");
        scoreField.setText(String.valueOf(application.getScore()));
        if (!application.getStatus().equals("Выполнен")) {
            scoreField.setText("Ожидайте выполнения");
            scoreField.setDisable(true);
        }
        scoreField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!p.matcher(newValue).matches()) scoreField.setText(oldValue);
            if (!newValue.equals("")) {
                application.setScore(Integer.valueOf(scoreField.getText()));
                calculateEmployeeScore();
            } else {
                application.setScore(100);
            }
        });
    }

    String returnUnit() {
        if (application.getJobType().equals("Кладка блоков")) return "м³";
        else return "м²";
    }

    void initLabels() {
        jobTypeLabel.setText(application.getJobType());
        FIOLabel.setText(employee.getLastname() + " " + employee.getFirstname() + " " + employee.getPatronymic());
        jobVolumeLabel.setText(application.getJobVolume() + returnUnit());
        priceLabel.setText(application.getPrice() + "$");
        statusLabel.setText(application.getStatus());
        scoreLabel.setText(employee.getScore() + "");
        phoneNumberLabel.setText(employee.getPhoneNumber());
    }

    Employee calculateEmployeeScore() {
        int scoreCount = 0;
        int scoreSum = 0;
        applications.get(idApplication).setScore(Integer.parseInt(scoreField.getText()));
        for (int i = 0; i < applications.size(); i++) {
            Application application = applications.get(i);
            if (application.getMasterId().equals(employee.getId()) && application.getScore() >= 0 && application.getScore() < 100) {//поиск заказов у нужного мастера
                scoreSum += application.score;
                scoreCount++;
            }
        }
        employee.setScore((int) ((double) scoreSum / (double) scoreCount)); //установить новую оценку рабочему
        return employee;
    }

}















