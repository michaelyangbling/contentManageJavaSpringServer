// package com.example.serverJavaWebDev.models;

// import javax.persistence.*;

// @Entity 
// @Table(name="imagewidgets")
// @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
// //receive from json: default constructor then setter only set matched, send: getter everything as much from json 
// public class ImageWidget extends Widget{

//     @Column(name = "src")
//     private String src;

//     public ImageWidget(){

//     }

//     public String getSrc(){
//         return this.src;
//     }

//     void  setText(String text){
//         this.src = src;
//     }


// }