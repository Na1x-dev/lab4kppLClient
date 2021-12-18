package com.example.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class Employee {
    public Integer id;
    public String firstname;
    public String lastname;
    public String patronymic;
    public String skill;
    public String phoneNumber;
    public Integer score;
    //private AnchorPane transactionPane = new AnchorPane();

    public Employee() {
        id = -1;
        firstname = "";
        lastname = "";
        patronymic = "";
        skill = "";
        phoneNumber = "";
        score = 0;
    }

    public Integer getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSkill() {
        return skill;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Employee parseUserFromJSON(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(JSON, Employee.class);
    }

    public String getJson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(Employee.this);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", skill='" + skill + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", score=" + score +
                '}';
    }
}
