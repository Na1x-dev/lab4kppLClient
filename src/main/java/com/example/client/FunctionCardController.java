package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.client.StaticFieldsAndRequests.*;

public class FunctionCardController {
    private static final Logger log = Logger.getLogger(FunctionCardController.class);
    Application application;
    HashMap<Integer, Integer> pricesForOne = new HashMap<Integer, Integer>();
    HashMap<Integer, String> functions = new HashMap<>();

    @FXML
    private Button doApplicationButton;

    @FXML
    private Label jobTypeLabel;

    @FXML
    private TextField jobVolumeField;

    @FXML
    private Label priceForOneLabel;

    @FXML
    private Label priceLabel;


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
    void doApplication(ActionEvent event) {
        if (!jobVolumeField.getText().equals("")) {
            if (Double.parseDouble(jobVolumeField.getText()) > 0) {
                application.setClientId(mainUser.getId());
                application.setJobVolume(Double.parseDouble(jobVolumeField.getText()));
                application.setStatus("Ожидание подтверждения");
                application.setJobType(functions.get(idButton));
                changeEmployee();
                calculatePrice();
                postResponseApplication(application);
                ((Node) (event.getSource())).getScene().getWindow().hide();
                log.info("окно ApplicationCard успешно закрыто");
                refresh();
            }
        }
    }

    @FXML
    void onChangeField(KeyEvent event) {

        //jobVolumeField.setText(takeNumberFromField());
   //     calculatePrice();
    }

    @FXML
    void initialize(){
        Pattern p = Pattern.compile("(\\d{1,5}\\.?\\d{0,2})?");
        jobVolumeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!p.matcher(newValue).matches()) jobVolumeField.setText(oldValue);
            calculatePrice();
        });

        application = new Application();
        initPricesForOne();
        initFunctions();
        jobTypeLabel.setText(functions.get(idButton));
        priceForOneLabel.setText(pricesForOne.get(idButton) + "$");
        priceLabel.setText("0$");
    }

    void changeEmployee() {
        employees = getResponseEmployees();
        int minCount = applications.size();
        int employeeId = 0;
        int maxScore = 0;
        for (int i = 0; i < employees.size(); i++) {
            int count = 0;
            for (int j = 0; j < applications.size(); j++) {
                if (applications.get(j).getMasterId().equals(i + 1)) {
                    count++;
                }
            }
            if ((count < minCount && employees.get(i).getSkill().equals(functions.get(idButton))) || (count == minCount
                    && employees.get(i).getSkill().equals(functions.get(idButton)) && maxScore< employees.get(i).getScore())) { //поиск работника с нужной специализацией и минимумом работы и лучшим рейтингом
                minCount = count;
                maxScore = employees.get(i).getScore();
                employeeId = employees.get(i).getId();
            }
        }
        application.setMasterId(employeeId);
    }

    void calculatePrice(){
        String volumeValue = jobVolumeField.getText();
        if(volumeValue.equals("")){
            volumeValue = "0";
        }
        Double price = pricesForOne.get(idButton) * Double.parseDouble(volumeValue);
        priceLabel.setText(price + "$");
        application.setPrice(price);
    }

    void initPricesForOne(){
        pricesForOne.put(1, 3);
        pricesForOne.put(2, 5);
        pricesForOne.put(3, 8);
        pricesForOne.put(4, 4);
    }

    void initFunctions(){
        functions.put(1, "Кладка блоков");
        functions.put(2, "Штукатурка стен");
        functions.put(3, "Кладка плитки");
        functions.put(4, "Покраска");
    }
}
