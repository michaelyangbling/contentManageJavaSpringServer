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

import com.example.serverJavaWebDev.models.Course;
import com.example.serverJavaWebDev.models.Module;

@RestController
@CrossOrigin(origins = "*",allowCredentials="True")
public class ModuleService {
    //@Autowired
    CourseService courseService=new CourseService();

    @GetMapping("/api/course/{courseId}/module")
    public List<Module> findModulesForCourse(
            @PathVariable("courseId") int courseId, HttpSession Session) {
        Course course = courseService.findCourseById(courseId, Session);
        if (course == null)
            return null;
        else
            return course.getModules();
            }
    @GetMapping("/api/module/{mid}")
    public Module findModuleById(
        @PathVariable("mid") int id, HttpSession session) {
            List<Course> courses=courseService.findAllCourses(session);
            if(courses==null)
                return null; //in case of session expired
            for(Course course: courses){
                for(Module module: course.getModules()){
                    if (module.getId()==id)
                        return module;
                }
            }
            return null;//in case of not finding module due to unknown error
        }
    
        @PutMapping("/api/module/{mid}")
        public Module updateModuleById(
            @PathVariable("mid") int id, HttpSession session, @RequestBody Module req) {
                Module module=this.findModuleById(id,session);
                if(module==null)
                    return null; //in case of session expired or other not find mod err
                module.setTitle(req.getTitle());
                return module;//in case of not finding module due to unknown error
            }

    @DeleteMapping("/api/module/{mid}")
    public Module deleteModule(
        @PathVariable("mid") int id, HttpSession session) {
            List<Course> courses=courseService.findAllCourses(session);
            if(courses==null)
                return null; //in case of session expired
            int i=0;
            for(Course course: courses){
                i=0;
                for(Module module: course.getModules()){
                    if (module.getId()==id){
                        course.getModules().remove(i);
                        return module;
                    }
                    i+=1;
                }
            }
            return null;//in case of not finding module due to unknown error
        }

    @PostMapping("/api/course/{courseId}/module")
    public Module addModuleForCourse(
        @PathVariable("courseId") int courseId, HttpSession Session, @RequestBody Module module) {
    List<Module> modules = this.findModulesForCourse(courseId, Session);
    if (modules == null)
        return null;
    else
        modules.add(module);
        return module;
}



}