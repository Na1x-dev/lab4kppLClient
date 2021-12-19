package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.client.StaticFieldsAndRequests.*;

public class SignInController {
    private static final Logger log = Logger.getLogger(SignInController.class);

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private Label logSignLabel;

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
    void backToLogIn(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage = new Stage();
            stage.setTitle("LogIn");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            log.info("окно hello-view успешно создано");
        } catch (IOException e) {
            log.error("окно hello-view не создано");
        }
    }

    @FXML
    void signInAction(ActionEvent event) {
        mainUser = new User();
        mainUser.setUsername(loginField.getText());
        mainUser.setPassword(passwordField1.getText());
        mainUser.setFirstname(firstnameField.getText());
        mainUser.setLastname(lastnameField.getText());
        if (validation(mainUser)) {
            User checkUser = postResponseUser(mainUser);
            if (checkUser == null) {
                logString = "Сервер недоступен";
            } else if (!checkUser.getUsername().equals(mainUser.getUsername())) {
                openMainWindow(event);
                loginField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0.4; -fx-border-color: #000000; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
            } else {
                loginField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 1.5; -fx-border-color: #ED254E; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
                logString = "Никнейм уже используется";
            }
        } else {
            logString = "Введите другие данные";
        }
        logSignLabel.setText(logString);
    }

    @FXML
    void onChangeField(KeyEvent event) {
        TextField someField = (TextField) event.getSource();
        logSignLabel.setText("");
        someField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0.4; -fx-border-color: #000000; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    void openMainWindow(ActionEvent event) {
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

    boolean validation(User user) {
        Pattern firstnamePattern = Pattern.compile(".{4,}");
        Pattern lastnamePattern = Pattern.compile(".{4,}");
        Pattern usernamePattern = Pattern.compile(".{4,}"); //[a-z0-9_-]{3,16}
        Pattern passwordPattern = Pattern.compile(".{4,}"); //(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}
        Pattern emailPattern = Pattern.compile(".{4,}"); //[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}

        Matcher firstnameMatcher = firstnamePattern.matcher(user.getFirstname());
        Matcher lastnameMatcher = lastnamePattern.matcher(user.getLastname());
        Matcher usernameMatcher = usernamePattern.matcher(user.getUsername());
        Matcher passwordMatcher = passwordPattern.matcher(user.getPassword());

        boolean isValidated = true;

        if (firstnameMatcher.matches()) {
            firstnameField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0.4; -fx-border-color: #000000; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
        } else {
            isValidated = false;
            //firstnameField.clear();
            firstnameField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 1.5; -fx-border-color: #ED254E; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
        }
        if (lastnameMatcher.matches()) {
            lastnameField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0.4; -fx-border-color: #000000; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
        } else {
            isValidated = false;
            //lastnameField.clear();
            lastnameField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 1.5; -fx-border-color: #ED254E; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
        }
        if (usernameMatcher.matches()) {
            loginField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0.4; -fx-border-color: #000000; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
        } else {
            isValidated = false;
            //loginField.clear();
            loginField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 1.5; -fx-border-color: #ED254E; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
        }
        if (passwordMatcher.matches()) {
            if (mainUser.getPassword().equals(passwordField2.getText())) {
                passwordField1.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0.4; -fx-border-color: #000000; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
                passwordField2.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 0.4; -fx-border-color: #000000; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
            } else {
                //passwordField.clear();
                passwordField1.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 1.5; -fx-border-color: #ED254E; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
                passwordField2.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 1.5; -fx-border-color: #ED254E; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
                isValidated = false;
            }
        } else {
            isValidated = false;
            //passwordField.clear();
            passwordField1.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 1.5; -fx-border-color: #ED254E; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
            passwordField2.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0; -fx-border-width: 1.5; -fx-border-color: #ED254E; -fx-border-radius: 0; -fx-prompt-text-fill: #999999;");
        }
        return isValidated;
    }

}


