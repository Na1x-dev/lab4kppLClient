package com.example.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {
    public int id;
    public String firstname;
    public String lastname;
    public String username;
    public String password;


    public User() {
        this.username = "";
        this.password = "";
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getJson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(User.this);
    }

    public static User parseUserFromJSON(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(JSON);
        return gson.fromJson(JSON, User.class);
    }
}