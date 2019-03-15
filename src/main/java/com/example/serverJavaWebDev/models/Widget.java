package com.example.serverJavaWebDev.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name="widgets")
public class Widget {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int wid;//to avoid redux id
    private String type;
    
    @ManyToOne()
    @JsonIgnore
    private Topic topic;

    private String src;
    
    private int size;
    private String text;

    private String para;

    private String url;
    private String title;

    private String items;
    private String list;

    public Widget() {}

    //methods are inherited
    public int getWId() {
        return wid;
    }
    public void setWId(int wid) {
        this.wid = wid;
    }
    public String getType() {
        return type;
    }
    public void getType(String type) {
        this.type = type;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
        if(!topic.getWidgets().contains(this)) {
        topic.getWidgets().add(this); }//only to set and send to database ? other also needed?
    }

    public Topic getTopic(){
        return this.topic;
    }

        public String getSrc(){
        return this.src;
    }

        public String getText(){
        return this.text;
    }

    void  setText(String text){
        this.text = text;
    }

    public int getSize(){
        return this.size;
    }

    public void setSize(int size){
        this.size = size;
    }

    public String getPara(){
        return this.para;
    }

    void  setPara(String para){
        this.para = para;
    }

    public String getUrl(){
        return this.url;
    }

    void  setUrl(String url){
        this.url = url;
    }

    public String getTitle(){
        return this.title;
    }

    void  setTitle(String url){
        this.title = title;
    }

    public String getItems(){
        return this.items;
    }

    void  setItems(String items){
        this.items = items;
    }

    public String getList(){
        return this.list;
    }

    void  setList(String list){
        this.list = list;
    }











}

