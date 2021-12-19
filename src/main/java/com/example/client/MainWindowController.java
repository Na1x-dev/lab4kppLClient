package com.example.client;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static com.example.client.StaticFieldsAndRequests.*;

public class MainWindowController {
    private static final Logger log = Logger.getLogger(MainWindowController.class);

    @FXML
    private AnchorPane LogInPane;

    @FXML
    private Button backToLogInButton;

    @FXML
    private AnchorPane applicationsField;

    @FXML
    private Label logLabel;

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
    void backToLogin(ActionEvent event) {
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
    private void openFunctionCard(ActionEvent event) {
        Button someButton = (Button) event.getSource();
        //component = components.get(Integer.parseInt(someButton.getId().substring(7)));
        idButton = Integer.parseInt(someButton.getId().substring(5));
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("FunctionCard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 700);
            Stage stage = new Stage();
            stage.setTitle("Function Card");
            stage.setScene(scene);
            stage.show();
            log.info("окно FunctionCard успешно создано");
        } catch (IOException e) {
            log.error("окно FunctionCard не создано");
        }
    }

    @FXML
    private void initialize() {
        renderApplications();
        refresh();
        Runnable task = () -> {
            while (true) {
                try {
                    renderApplications();
                    refresh();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    void renderApplications() {

        for (int i = 0; i < applications.size(); i++) {
            AnchorPane anchorPane;
            int y = 70 * i + 10 * i;
            anchorPane = applications.get(i).createApplicationWidget(applicationsField, y);
            initializeApplications(anchorPane, i); // настройка действий для виджетов заявок
        }
    }

    void initializeApplications(AnchorPane applicationPane, int i) { // настройка действий для виджетов заявок
        applicationPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                refresh();
                renderApplications();
                viewApplication(event, i);
                applicationPane.setStyle("-fx-background-color: #eee; -fx-border-color: #000; -fx-border-width: 0.4;");
            }
        });
        applicationPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                applicationPane.setStyle("-fx-background-color: #fff; -fx-border-color: #000; -fx-border-width: 0.4;");
            }
        });
        applicationPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                applicationPane.setStyle("-fx-background-color: #fff; -fx-border-color: #000; -fx-border-width: 0.4;");
            }
        });
        applicationPane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                applicationPane.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #000; -fx-border-width: 0.4;");
            }
        });
    }

    @FXML
    void viewApplication(MouseEvent event, int i) {
        idApplication = i;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ApplicationCard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 700);
            Stage stage = new Stage();
            stage.setTitle("application Card");
            stage.setScene(scene);
            stage.show();
            log.info("окно ApplicationCard успешно создано");
        } catch (IOException e) {
            log.error("окно ApplicationCard не создано");
        }
    }

    void refresh() {
        try {
            applications = getResponseApplications(mainUser);
            Platform.runLater(() -> {
                logLabel.setText("");
                isConnected = true;
            });
        } catch (NullPointerException | IOException e){
            Platform.runLater(() -> {
                isConnected = false;
                applicationsField.getChildren().clear();
                logLabel.setText("В данный момент сервер недоступен");
            });
        }
    }
}






