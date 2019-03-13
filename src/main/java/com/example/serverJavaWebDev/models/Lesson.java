package com.example.serverJavaWebDev.models;

import java.util.ArrayList;
import java.util.List;
import com.example.serverJavaWebDev.models.Topic;

public class Lesson {
    public static int autoIncrement = 0;
    private int id = autoIncrement++;
    private String title;
    private List<Topic> topics = new ArrayList<Topic>();
    public List<Topic> getTopics() {
        return topics;
    }
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
    public Lesson() {}
    public Lesson(String title) {
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