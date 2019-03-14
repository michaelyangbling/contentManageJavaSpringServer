package com.example.serverJavaWebDev.models;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity 
@Table(name="lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id ;

    private String title;

    @OneToMany(mappedBy="lesson")
    private List<Topic> topics = new ArrayList<Topic>();
    public List<Topic> getTopics() {
        return topics;
    }
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    @ManyToOne()
    @JsonIgnore
    private Module module;

    public Lesson() {}
    public Lesson(String title) {
        this.title = title;
    }
    public Lesson(String title, int id) {
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

    public void setModule(Module module) {
        this.module = module;
        if(!module.getLessons().contains(this)) {
        module.getLessons().add(this); }//only to set and send to database ? other also needed?
    }

    public void hasTopic(Topic topic) //set and avoid infinite loop
    { this.topics.add(topic); 
        if(topic.getLesson() != this) {
            topic.setLesson(this); }//not need
    }

    public Module getModule() {
        return this.module;
    }
}