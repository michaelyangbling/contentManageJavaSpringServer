package com.example.serverJavaWebDev.services;

import java.util.List;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.joran.conditional.ElseAction;

import com.example.serverJavaWebDev.models.Course;
import com.example.serverJavaWebDev.models.Module;
import com.example.serverJavaWebDev.models.Lesson;


@RestController
@CrossOrigin(origins = "*",allowCredentials="True")

public class LessonService {
    ModuleService moduleService=new ModuleService();
    CourseService courseService=new CourseService();

    @GetMapping("/api/module/{mid}/lesson")
    public List<Lesson> findAllLessons(@PathVariable("mid") int mid, HttpSession session){
        Module module=moduleService.findModuleById(mid, session);
        if (module==null)//if module not found, due to session expire not module not exist
        {
            return null;
        }
        else{
            return module.getLessons();
        }

    }
//GET /api/lesson/{lid}
@GetMapping("/api/lesson/{lid}")
    public Lesson findLessonById(@PathVariable("lid") int lid, HttpSession session){
        List<Course> courses=courseService.findAllCourses(session);
        if(courses==null)
            return null; //in case of session expired
        for(Course course: courses){
            for(Module module: course.getModules()){
                for(Lesson lesson:module.getLessons()){
                    if (lesson.getId()==lid)
                        return lesson;
                }
                
            }
        }
        return null;//in case of not finding lesson due to unknown error
    }
    //POST /api/module/{mid}/lesson
    
    @PostMapping("/api/module/{mid}/lesson")
    public Lesson createLesson(@PathVariable("mid") int mid, HttpSession session, @RequestBody Lesson lesson){
        List<Lesson> lessons=this.findAllLessons(mid, session);
        if(lessons==null)//if module/lessons not found, due to session expire not module not exist
            return null;
        else{
            lessons.add(lesson);
            return lesson;
        }


    }

    //PUT /api/lesson/{lid}
    @PutMapping("/api/lesson/{lid}")
    public Lesson updateLesson(@PathVariable("lid") int lid, HttpSession session, @RequestBody Lesson newLesson){
        Lesson lesson=this.findLessonById(lid, session);
        if (lesson==null)
            return null; //lesson not found
        else{
            lesson.setTitle(newLesson.getTitle());
            return lesson;
        }
    }

    // /api/lesson/{lid}
    @DeleteMapping("/api/lesson/{lid}")
    public Lesson deleteLesson(@PathVariable("lid") int lid, HttpSession session){
        List<Course> courses=courseService.findAllCourses(session);
        if(courses==null)
            return null; //in case of session expired
        int i=0;
        for(Course course: courses){
            for(Module module: course.getModules()){
                i=0;
                for(Lesson lesson:module.getLessons()){
                    if (lesson.getId()==lid){
                        module.getLessons().remove(i);
                        return lesson;}
                    i+=1;
                }
                
            }
        }
        return null;//in case of not finding lesson due to unknown error
    }

}