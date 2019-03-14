package com.example.serverJavaWebDev.models;

import java.util.ArrayList;
import java.util.List;
import com.example.serverJavaWebDev.models.Lesson;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity 
@Table(name="modules")
public class Module {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToOne()
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy="module")
    private List<Lesson> lessons = new ArrayList<Lesson>();

    public List<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
    public Module() {}
    public Module(String title) {
        this.title = title;
    }
    public Module(String title, int id) {
        this.title = title;
        this.id = id;
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

    public void setCourse(Course course) {
        this.course = course;
        if(!course.getModules().contains(this)) {
        course.getModules().add(this); }//only to set and send to database ? other also needed?
    }

    public Course getCourse() {
        return this.course;
    }

    public void hasLesson(Lesson lesson) //set and avoid infinite loop
    { this.lessons.add(lesson); 
        if(lesson.getModule() != this) {
            lesson.setModule(this); }//not need
    }
}