package com.example.serverJavaWebDev.repos;

import com.example.serverJavaWebDev.models.*;
import org.springframework.data.repository.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.*;
import org.springframework.data.repository.query.Param;
//java bean convention: receive: default constructor and setter,  send json: getter
import org.springframework.transaction.annotation.EnableTransactionManagement;

public interface LessonRepository
        extends CrudRepository<Lesson, Integer> {

        // @Query("SELECT user FROM User user WHERE user.username=:username")
        // public List<User> findUserByUsername(@Param("username") String username);
        
        // @Modifying
        // @Query("DELETE course from Course course WHERE course.user_id=:user_id AND course.id=:id") 
        // public void deleteUserCourse(@Param("user_id") int uid, 
        // @Param("id") int cid);
        
        // @Modifying
        // @Query("UPDATE Course course  set course.title =:title WHERE course.user_id=:user_id AND course.id=:id") 
        // public void updateUserCourse(@Param("user_id") int uid, 
        // @Param("id") int cid, String title);
        

        
        // @Query("SELECT course from Course course WHERE course.user.id=:user_id AND course.id=:id") 
        // public List<Course> selectUserCourse(@Param("user_id") int uid, 
        // @Param("id") int cid);
        
}
//@Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
