package com.codingstuff.shoeapp.utils.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data_db")
public class dataClass {
    public dataClass(String nameArticle, int price, String destination, String from, State state,String phone) {
        this.nameArticle = nameArticle;
        this.price = price;
        this.destination = destination;
        this.from = from;
        this.state = state;
        this.phone = phone;

    }
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    private String nameArticle;
    private int price;
    private String destination;
    private String from;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private State state;

    public String getNameArticle() {
        return nameArticle;
    }

    public int getPrice() {
        return price;
    }

    public String getDestination() {
        return destination;
    }

    public String getFrom() {
        return from;
    }

    public void setNameArticle(String nameArticle) {
        this.nameArticle = nameArticle;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
