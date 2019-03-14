package com.example.serverJavaWebDev.models;

import java.util.ArrayList;
import java.util.List;
import com.example.serverJavaWebDev.models.Widget;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity 
@Table(name="topics")
public class Topic {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String title;
    // private List<Widget> widgets = new ArrayList<Widget>();
    // public List<Widget> getWidgets() {
    //     return widgets;
    // }
    // public void setWidgets(List<Widget> widgets) {
    //     this.widgets = widgets;
    // }
    @ManyToOne()
    @JsonIgnore
    private Lesson lesson;
    public Topic() {}
    public Topic(String title) {
        this.title = title;
    }
    public Topic(String title, int id) {
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
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
        if(!lesson.getTopics().contains(this)) {
        lesson.getTopics().add(this); }//only to set and send to database ? other also needed?
    }

    public Lesson getLesson(){
        return this.lesson;
    }
}