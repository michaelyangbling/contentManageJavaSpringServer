package com.example.serverJavaWebDev.repos;

import com.example.serverJavaWebDev.models.*;
import org.springframework.data.repository.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.*;
import org.springframework.data.repository.query.Param;
//java bean convention: receive: default constructor and setter,  send json: getter
public interface UserRepository
        extends CrudRepository<User, Integer> {
        
        @Query("SELECT user FROM User user WHERE user.username=:username")
        public List<User> findUserByUsername(@Param("username") String username);
        
        @Query("SELECT user FROM User user WHERE user.username=:username AND user.password=:password") 
        public List<User> findUserByCredentials(@Param("username") String username, 
        @Param("password") String password);

        
}