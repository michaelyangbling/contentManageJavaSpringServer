package com.example.serverJavaWebDev.models;

import java.util.ArrayList;
import java.util.List;
import com.example.serverJavaWebDev.models.Course;
import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    public static int autoIncrement = 0;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
//    private int id = autoIncrement++;
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    // private List<Course> courses = new ArrayList<Course>();
    @OneToMany(mappedBy="user")
   private List<Course> courses = new ArrayList();
//mvn spring-boot:run
    public User() {}
//    public User(String username) {
////        this.username = username;
////    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String firstname, String lastname, String username, String password) {
        this(username, password);
        this.firstname = firstname;
        this.lastname = lastname;
    }
//
//    public List<Course> getCourses() {
//        return courses;
//    }
//    public void setCourses(List<Course> courses) {
//        this.courses = courses;
//    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    //user getters to return to repsonse
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void hasCourse(Course course)
    { this.courses.add(course); 
        if(course.getUser() != this) {
            course.setUser(this); }//not need
}

}