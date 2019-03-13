package com.example.serverJavaWebDev.models;

import java.util.ArrayList;
import java.util.List;
import com.example.serverJavaWebDev.models.Widget;


public class Topic {
    public static int autoIncrement = 0;
    private int id = autoIncrement++;
    private String title;
    private List<Widget> widgets = new ArrayList<Widget>();
    public List<Widget> getWidgets() {
        return widgets;
    }
    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }
    public Topic() {}
    public Topic(String title) {
        this.title = title;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}