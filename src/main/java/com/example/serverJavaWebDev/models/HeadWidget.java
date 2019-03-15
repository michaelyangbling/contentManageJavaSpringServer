// package com.example.serverJavaWebDev.models;

// import javax.persistence.*;

// @Entity 
// @Table(name="headwidgets")
// @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
// //receive from json: default constructor then setter only set matched, send: getter everything as much from json 
// public class HeadWidget extends Widget{

//     @Column(name = "text")
//     private String text;

//     @Column(name = "size")
//     private int size;

//     public HeadWidget(){

//     }

//     public String getText(){
//         return this.text;
//     }

//     void  setText(String text){
//         this.text = text;
//     }

//     public int getSize(){
//         return this.size;
//     }

//     public void setSize(int size){
//         this.size = size;
//     }





// }