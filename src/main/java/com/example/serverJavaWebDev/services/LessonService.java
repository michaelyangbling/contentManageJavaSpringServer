package com.example.serverJavaWebDev.services;

import java.util.List;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.serverJavaWebDev.repos.LessonRepository;
import com.example.serverJavaWebDev.repos.ModuleRepository;
import com.example.serverJavaWebDev.models.Lesson;


@RestController
@CrossOrigin(origins = "*",allowCredentials="True")

public class LessonService {
    @Autowired
    ModuleService moduleService;

    @Autowired
    CourseService courseService;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    ModuleRepository moduleRepository;

    // @GetMapping("/api/module/{mid}/lesson")
    // public List<Lesson> findAllLessons(@PathVariable("mid") int mid, HttpSession session){
    //     Module module=moduleService.findModuleById(mid, session);
    //     if (module==null)//if module not found, due to session expire not module not exist
    //     {
    //         return null;
    //     }
    //     else{
    //         return module.getLessons();
    //     }

    // }
//GET /api/lesson/{lid}
// @GetMapping("/api/lesson/{lid}")
    public Lesson findLessonById(@PathVariable("lid") int lid, HttpSession session){
        List<Course> courses=courseService.findAllCourses(session);
        if(courses==null)
            return null; //in case of session expired
        for(Course course: courses){ //better to write joins
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
        Module module=moduleService.findModuleById(mid, session);
        if(module==null)//if module/lessons not found, due to session expire or module not exist
            return null;
        else{
            //create lesson
            lesson = lessonRepository.save( lesson);

            module.hasLesson(lesson);
            module = moduleRepository.save(module);
            //this module just need to render ID, such a frame
            return new Lesson(lesson.getTitle(), lesson.getId() );
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
            lesson = lessonRepository.save(lesson);
            return new Lesson(lesson.getTitle(), lesson.getId() );
        }
    }

    // /api/lesson/{lid}
    @DeleteMapping("/api/lesson/{lid}")
    public Lesson deleteLesson(@PathVariable("lid") int lid, HttpSession session){
        List<Course> courses=courseService.findAllCourses(session);
        if(courses==null)
            return null; //in case of session expired
        for(Course course: courses){
            for(Module module: course.getModules()){
                for(Lesson lesson: module.getLessons()){
                if (lesson.getId()==lid){
                        lessonRepository.deleteById(lid);
                        return new Lesson(lesson.getTitle(), lesson.getId() );//just frame is enough
                    }
                }
            }
        }
        return null;//in case of not finding module due to unknown error
    }



}