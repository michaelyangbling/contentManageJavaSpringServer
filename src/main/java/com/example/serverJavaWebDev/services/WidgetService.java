package com.example.serverJavaWebDev.services;

import java.util.List;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
//setter from jpa: [](other class) if no match   to jpa:null or [] -> ok
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

import com.example.serverJavaWebDev.models.*;
import com.example.serverJavaWebDev.repos.*;



@RestController
@CrossOrigin(origins = "*",allowCredentials="True")

public class WidgetService {
    @Autowired
    TopicRepository topicRepository;

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    TopicService topicService;

    @Transactional
    @PutMapping("/api/topic/{tid}/savewidgets")
    public Widget updateWidgets(@PathVariable("tid") int tid, HttpSession session, @RequestBody List<Widget> widgets){
        System.out.println(0);
        Topic topic=topicService.findTopicById(tid, session);
        if(topic==null)//if topics not found, due to session expire not module not exist
            return null;
        else{
            //delete topic's widgets then add all
            for( Widget widget: widgetRepository.selectWidgets( topic.getId() ) ){
                widgetRepository.deleteById( widget.getWId() );
            }
            System.out.println(1);
            System.out.println(tid);
            //create widgets
            for(Widget widget: widgets){
                widget = widgetRepository.save( widget);
                System.out.println(widget.getWId());
                topic.hasWidget(widget);
            }
            topicRepository.save(topic);

            return new Widget(); //
        }


    }
}
