package com.example.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class Application {
    public Integer id;
    public Integer masterId;
    public String jobType;
    public double jobVolume;
    public double price;
    public String status;
    public Integer clientId;
    //private AnchorPane applicationPane = new AnchorPane();

    public Application() {
        masterId = -1;
        jobType = "ничегонеделание";
        jobVolume = 100;
        price = 99.99;
        status = "выполняется";
        clientId = -1;
    }

    public Integer getId() {
        return id;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public double getJobVolume() {
        return jobVolume;
    }

    public String getJobType() {
        return jobType;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setJobVolume(double jobVolume) {
        this.jobVolume = jobVolume;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public AnchorPane createApplicationWidget(AnchorPane applicationsField, int y) {
        Label jobTypeLabel = new Label(this.jobType);
        Label statusLabel = new Label(this.status);
        Label priceLabel = new Label(String.valueOf(this.price) + "$");
        AnchorPane applicationPane = new AnchorPane(jobTypeLabel, statusLabel, priceLabel);
        applicationPane.setLayoutY(y);
        applicationPane.setMinSize(430, 150);
        applicationPane.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #000; -fx-border-width: 0.4;");

        jobTypeStyle(jobTypeLabel);
        statusStyle(statusLabel);
        priceStyle(priceLabel);
        Platform.runLater(() -> {
            applicationsField.getChildren().add(applicationPane);
        });
        return applicationPane;
    }

    void jobTypeStyle(Label nameOfApplicationLabel){
        Font underdogFont = new Font("Montserrat ExtraLight", 28);
        nameOfApplicationLabel.fontProperty().set(underdogFont);
        nameOfApplicationLabel.setLayoutX(15);
        nameOfApplicationLabel.setLayoutY(15);
        nameOfApplicationLabel.setMinSize(400, 0);
        nameOfApplicationLabel.setMaxWidth(400);
        nameOfApplicationLabel.setAlignment(Pos.CENTER_LEFT);
        nameOfApplicationLabel.setTextOverrun(OverrunStyle.ELLIPSIS);
    }

    void statusStyle(Label checkNumberLabel){
        Font underdogFont = new Font("Montserrat ExtraLight", 16);
        checkNumberLabel.fontProperty().set(underdogFont);
        checkNumberLabel.setLayoutX(15);
        checkNumberLabel.setLayoutY(110);
    }

    void priceStyle(Label priceLabel){
        Font underdogFont = new Font("Montserrat ExtraLight", 22);
        priceLabel.fontProperty().set(underdogFont);
        priceLabel.setLayoutX(250);
        priceLabel.setLayoutY(100);
        priceLabel.setMinSize(160, 0);
        priceLabel.setAlignment(Pos.CENTER_RIGHT);
    }

    public Application parseUserFromJSON(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(JSON, Application.class);
    }

    public String getJson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(Application.this);
    }


}
