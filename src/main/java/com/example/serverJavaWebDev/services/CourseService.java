package com.example.serverJavaWebDev.services;

import java.util.List;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.serverJavaWebDev.models.Course;
import com.example.serverJavaWebDev.models.User;
import com.example.serverJavaWebDev.repos.CourseRepository;
import com.example.serverJavaWebDev.repos.UserRepository;

@RestController
@CrossOrigin(origins="*",allowCredentials="True")
public class CourseService {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    //List<Course> courses = null;
    @GetMapping("/api/user/courses")
    public List<Course> findAllCourses(HttpSession session) {
        User user=(User)session.getAttribute("currentUser");
        if (user == null) {
            return null; // Not logged in, redirect to login page.
        } else {
            user = userRepository.findById( user.getId() ).get();
            // System.out.println(user.getCourses());
            // System.out.println(user.getCourses().getClass().getSimpleName());
            return user.getCourses();
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
            user = userRepository.findById( user.getId() ).get();//retrieve users' newest info
            
            //create course
            course = courseRepository.save(course);
            System.out.println(course.getUser());
            

            //what if go drom course side? same!!
            // course.setUser(user);
            // courseRepository.save(course);
            // System.out.println( course.getUser().getCourses() );
            // return course.getUser().getCourses();

            user.hasCourse(course);
            user = userRepository.save(user);
            return user.getCourses();
        }
    }


    @DeleteMapping("/api/user/course/{numId}")
    public List<Course> deleteCourse(
            HttpSession session,
            @PathVariable("numId") int cid) {
        if (session.getAttribute("currentUser") == null) {
            return null; // Not logged in, redirect to login page.
        } else {
            User user=(User)session.getAttribute("currentUser");
            if( courseRepository.selectUserCourse(user.getId(), cid).size() >0 ){  //course belongs to user
            courseRepository.deleteById(cid);
            return this.findAllCourses(session);
            }
            return null;
            
        }
    }

    @PutMapping("/api/user/course/{numId}")
    public Integer updateCourse(
            HttpSession session,
            @RequestBody Course course,
            @PathVariable("numId") int cid) {
                String title=course.getTitle();
        if (session.getAttribute("currentUser") == null) {
            return null; // Not logged in, redirect to login page.
        } else {
            int i=0;
            User user=(User)session.getAttribute("currentUser");
            if( courseRepository.selectUserCourse(user.getId(), cid).size() >0 ){  //course belongs to user
                course = courseRepository.findById(cid).get();
                course.setTitle( title );
                courseRepository.save( course );
                return 1;
                }
            // courseRepository.updateUserCourse(user.getId(), cid, course.getTitle());
            return null;//change success
        }
    }

    // @GetMapping("/api/user/course/{numId}")
    public Course findCourseById(int cid, HttpSession session) {
        if (session.getAttribute("currentUser") == null) {
            return null; // Not logged in, redirect to login page.
        } else {
            int i=0;
            User user=(User)session.getAttribute("currentUser");
            System.out.println( user );
            System.out.println( user.getId() );
            System.out.println( courseRepository );
            System.out.println( courseRepository.selectUserCourse(user.getId(), cid) );
            if( courseRepository.selectUserCourse(user.getId(), cid).size() >0 ){  //course(even after deletion) belongs to user
                return courseRepository.findById(cid).get(); 
                }
            return null;//not found
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