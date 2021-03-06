package com.example.serverJavaWebDev.models;

import java.util.ArrayList;
import java.util.List;
import com.example.serverJavaWebDev.models.Module;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity 
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToOne()
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy="course")
    private List<Module> modules = new ArrayList<Module>();
    public Course(String title) {
        this.title = title;
    }
    @Transient
    @JsonSerialize
    private String username;

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(String username){
        return this.username;
    }
    // public Course(int i, String title) {
    //     this.id = i; this.title = title;
    // }
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

    public void setUser(User user) {
        this.user = user;
        if(!user.getCourses().contains(this)) {
        user.getCourses().add(this); }//only to set and send to database ? other also needed?
    }

    public User getUser() {
        return this.user;
    }

    public void hasModule(Module module) //set and avoid infinite loop
    { this.modules.add(module); 
        if(module.getCourse() != this) {
            module.setCourse(this); }//not need
}


}