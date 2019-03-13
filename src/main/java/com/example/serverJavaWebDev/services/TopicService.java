// package com.example.serverJavaWebDev.services;

// import java.util.List;
// import javax.servlet.http.HttpSession;
// import java.util.ArrayList;

// //import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RestController;

// import ch.qos.logback.core.joran.conditional.ElseAction;

// import com.example.serverJavaWebDev.models.Course;
// import com.example.serverJavaWebDev.models.Module;
// import com.example.serverJavaWebDev.models.Lesson;
// import com.example.serverJavaWebDev.models.Topic;



// @RestController
// @CrossOrigin(origins = "*",allowCredentials="True")

// public class TopicService {
//     LessonService lessonService=new LessonService();
//     ModuleService moduleService=new ModuleService();
//     CourseService courseService=new CourseService();
//     // /api/lesson/{lid}/topic
//     @GetMapping("/api/lesson/{lid}/topic")
//     public List<Topic> findAllTopics(@PathVariable("lid") int lid, HttpSession session){
//         Lesson lesson=lessonService.findLessonById(lid, session);
//         if (lesson==null)//if lesson not found, due to session expire not module not exist
//         {
//             return null;
//         }
//         else{
//             return lesson.getTopics();
//         }

//     }
// //GET /api/topic/{tid}
// @GetMapping("/api/lesson/{tid}")
//     public Topic findTopicById(@PathVariable("tid") int tid, HttpSession session){
//         List<Course> courses=courseService.findAllCourses(session);
//         if(courses==null)
//             return null; //in case of session expired
//         for(Course course: courses){
//             for(Module module: course.getModules()){
//                 for(Lesson lesson:module.getLessons()){
//                     for(Topic topic:lesson.getTopics()){
//                         if (topic.getId()==tid)
//                             return topic;
//                     }
//                 }
                
//             }
//         }
//         return null;//in case of not finding topic due to unknown error
//     }

//     // /api/lesson/{lid}/topic
//     @PostMapping("/api/lesson/{lid}/topic")
//     public Topic createLesson(@PathVariable("lid") int lid, HttpSession session, @RequestBody Topic topic){
//         List<Topic> topics=this.findAllTopics(lid, session);
//         if(topics==null)//if topics not found, due to session expire not module not exist
//             return null;
//         else{
//             topics.add(topic);
//             return topic;
//         }


//     }

//     //PUT /api/topic/{lid}
//     @PutMapping("/api/topic/{tid}")
//     public Topic updateLesson(@PathVariable("tid") int tid, HttpSession session, @RequestBody Lesson newTopic){
//         Topic topic=this.findTopicById(tid, session);
//         if (topic==null)
//             return null; //topic not found
//         else{
//             topic.setTitle(newTopic.getTitle());
//             return topic;
//         }
//     }

//     // /api/topic/{lid}
//     @DeleteMapping("/api/topic/{tid}")
//     public Topic deleteLesson(@PathVariable("tid") int tid, HttpSession session){
//         List<Course> courses=courseService.findAllCourses(session);
//         if(courses==null)
//             return null; //in case of session expired
//         int i=0;
//         for(Course course: courses){
//             for(Module module: course.getModules()){
//                 i=0;
//                 for(Lesson lesson:module.getLessons()){
//                     for(Topic topic:lesson.getTopics()){
//                     if (topic.getId()==tid){
//                         lesson.getTopics().remove(i);
//                         return topic;}
//                     i+=1;
//                 }
                
//             }
//         }
//     }
//         return null;//in case of not finding topic due to unknown error
//     }

// }