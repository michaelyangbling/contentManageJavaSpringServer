package com.example.serverJavaWebDev.services;

import java.util.List;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.serverJavaWebDev.models.Course;
import com.example.serverJavaWebDev.models.User;

@RestController
@CrossOrigin(origins="*",allowCredentials="True")
public class CourseService {

    //@Autowired
    UserService userService;

    //List<Course> courses = null;
    @GetMapping("/api/user/courses")
    public List<Course> findAllCourses(HttpSession session) {
        if (session.getAttribute("currentUser") == null) {
            return null; // Not logged in, redirect to login page.
        } else {
            return ((User)session.getAttribute("currentUser")).getCourses();
        }
    }

    @PostMapping("/api/user/course")
    public List<Course> createCourse(
            HttpSession session,
            @RequestBody Course course) {
        if (session.getAttribute("currentUser") == null) {
            return null; // Not logged in, redirect to login page.
        } else {
            User user=(User)session.getAttribute("currentUser");
            user.getCourses().add(course);
            return user.getCourses();
        }
    }

    @DeleteMapping("/api/user/course/{numId}")
    public List<Course> deleteCourse(
            HttpSession session,
            @PathVariable("numId") int id) {
        if (session.getAttribute("currentUser") == null) {
            return null; // Not logged in, redirect to login page.
        } else {
            int i=0;
            User user=(User)session.getAttribute("currentUser");
            List<Course> courses=user.getCourses();
            while(i<courses.size()){
                if (courses.get(i).getId()==id){
                    courses.remove(i); //if user not exist, return some other info to let client refresh page
                    return courses;}
                i=i+1;
            }
            return null;
        }
    }

//    public Course findCourseById(int courseId) {
//        List<User> users = userService.findAllUsers();
//        for(User user: users) {
//            List<Course> courses = user.getCourses();
//            for(Course course: courses) {
//                if(course.getId() == courseId)
//                    return course;
//            }
//        }
//        return null;
//    }
}