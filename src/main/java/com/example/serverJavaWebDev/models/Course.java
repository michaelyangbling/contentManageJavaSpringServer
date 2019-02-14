package com.example.serverJavaWebDev.models;

import java.util.ArrayList;
import java.util.List;
import com.example.serverJavaWebDev.models.Module;

public class Course {
    public static int autoIncrement = 0;
    private int id = autoIncrement++;
    private String title;
    private List<Module> modules = new ArrayList<Module>();
    public Course(String title) {
        this.title = title;
    }
    public Course(int i, String title) {
        this.id = i; this.title = title;
    }
    public Course() {}
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
    public List<Module> getModules() {
        return modules;
    }
    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

}