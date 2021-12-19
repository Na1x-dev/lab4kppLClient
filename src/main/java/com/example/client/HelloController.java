package com.example.client;


import com.example.client.StaticFieldsAndRequests;
import com.example.client.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;

import static com.example.client.StaticFieldsAndRequests.*;


public class HelloController {
    private static final Logger log = Logger.getLogger(HelloController.class);

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label logLoginLabel;

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
    void openSignInWindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("SignIn.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage = new Stage();
            stage.setTitle("SignIn");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            log.info("окно SignIn успешно создано");
        } catch (IOException e) {
            log.error("окно SignIn не создано");
        }
    }

    public void openMainWindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
            Stage stage = new Stage();
            stage.setTitle("Main Window");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            log.info("окно mainWindow успешно создано");
        } catch (IOException e) {
            log.error("окно mainWindow не создано");
        }
    }

    @FXML
    void onChangeField() {
        logLoginLabel.setText("");
        loginField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0.4; -fx-border-color: #000000; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
        passwordField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0.4; -fx-border-color: #000000; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
    }

    @FXML
    void loginAction(ActionEvent event) {
        mainUser = new User();
        mainUser.setUsername(loginField.getText());
        mainUser.setPassword(passwordField.getText());
        User checkUser = getResponseUser(mainUser);
        if(checkUser == null) {
            logString = "Сервер недоступен";
        }
        else if (checkUser.getPassword().equals(mainUser.getPassword()) && !mainUser.getUsername().equals("") && !mainUser.getPassword().equals("")) { // все верно
            mainUser = checkUser;
            openMainWindow(event);
        }
        else {
            passwordField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 1.5; -fx-border-color: #ED254E; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
            loginField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 1.5; -fx-border-color: #ED254E; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
            logString = "Неверные логин и(или) пароль";
        }
        logLoginLabel.setText(logString);
    }
}
