package com.example.serverJavaWebDev.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.serverJavaWebDev.models.User;

@RestController
@CrossOrigin(origins="*",allowCredentials="True")
public class UserService {

    static List<User> users = new ArrayList<User>();


    @PostMapping("/api/register")
    public User register(
            @RequestBody User user,
            HttpSession session) {
        for(User oldUser: users){
            if(oldUser.getUsername().equals(user.getUsername()))
                    return new User();//prevent null
        }
        session.setAttribute("currentUser", user);
        users.add(user);//concurency?
        return user;
    }
    //https://stackoverflow.com/questions/1577236/how-to-check-whether-a-user-is-logged-in-or-not-in-servlets

    @GetMapping("/api/checkLogIn")
    public int checkLogIn(HttpSession session) {
        if (session.getAttribute("currentUser") == null) {
            return 0; // Not logged in, redirect to login page.
        } else {
            return 1;
        }

    }

    @GetMapping("/api/profile")
    public User updateUser(HttpSession session) {
        if (session.getAttribute("currentUser") == null) {
            return new User(); // Not logged in, redirect to login page.
        } else {
            return (User)session.getAttribute("currentUser");
        }
    }
    @PutMapping("/api/updateProfile")
    public User profile(HttpSession session,@RequestBody User user) {
        if (session.getAttribute("currentUser") == null) {
            return new User(); // Not logged in, redirect to login page.
        } else {
            User oldUser=(User)session.getAttribute("currentUser");
            oldUser.setFirstname(user.getFirstname());
            oldUser.setLastname(user.getLastname());
            return oldUser;
        }
    }

    @PostMapping("/api/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @PostMapping("/api/login")
    public User login(
            @RequestBody User credentials,
            HttpSession session) {
        for (User user : users) {
            if( user.getUsername().equals(credentials.getUsername())
                    && user.getPassword().equals(credentials.getPassword())) {
                session.setAttribute("currentUser", user);
                return user;
            }
        }
        return new User();//prevent null
    }
}