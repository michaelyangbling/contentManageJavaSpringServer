package com.example.serverJavaWebDev.models;

import java.util.ArrayList;
import java.util.List;
import com.example.serverJavaWebDev.models.Module;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity 
@Table(name="courses")
public class Course {
    public static int autoIncrement = 0;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToOne()
    @JsonIgnore
    private User user;
    // private List<Module> modules = new ArrayList<Module>();
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
    // public List<Module> getModules() {
    //     return modules;
    // }
    // public void setModules(List<Module> modules) {
    //     this.modules = modules;
    // }

    public void setUser(User user) {
        this.user = user;
        if(!user.getCourses().contains(this)) {
        user.getCourses().add(this); }//only to set and send to database ? other not needed?
    }

    public User getUser() {
        return this.user;
    }


}