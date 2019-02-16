package com.example.serverJavaWebDev.models;

import java.util.ArrayList;
import java.util.List;

public class Widget {
    public static int autoIncrement = 0;
    private int id = autoIncrement++;
    public Widget() {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}