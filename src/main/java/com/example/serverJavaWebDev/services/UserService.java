package com.example.serverJavaWebDev.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.serverJavaWebDev.models.User;
import com.example.serverJavaWebDev.repos.UserRepository;

@RestController
@CrossOrigin(origins="*",allowCredentials="True")
public class UserService {
    @Autowired
    UserRepository userRepository;
    static List<User> users = new ArrayList<User>();

    //@GetMapping("/api/user/{uname}")
    private User getUserByName(String uname){
        List<User> users= userRepository.findUserByUsername(uname);
        if(users.size() != 0 ){
            return users.get(0);
        }
        return null;
    }
    @Transactional //can be better
    @PostMapping("/api/register")
    public User register(
            @RequestBody User user,
            HttpSession session) {
            if( this.getUserByName( user.getUsername() )!= null) { // such username exists
                    return new User();//prevent null
        }
        
        //concurency?
        user=userRepository.save(user);//id is here
        System.out.println(user.getFirstname());
        session.setAttribute("currentUser", user);
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
            User user = (User)session.getAttribute("currentUser");
            return userRepository.findById( user .getId() ).get();
        }
    }

    @PutMapping("/api/updateProfile")
    public User profile(HttpSession session,@RequestBody User user) {
        if (session.getAttribute("currentUser") == null) {
            return new User(); // Not logged in, redirect to login page.
        } else {
            User oldUser=(User)session.getAttribute("currentUser");
            oldUser = userRepository.findById( oldUser .getId() ).get();//to prevent change of database during session

            oldUser.setFirstname(user.getFirstname());
            oldUser.setLastname(user.getLastname());
            userRepository.save(oldUser);
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
        List<User> users = userRepository.findUserByCredentials(credentials.getUsername(), credentials.getPassword());
        if( users.size() ==0 ){
        return new User();//prevent null, not found match
        }
        session.setAttribute("currentUser", users.get(0));
        return users.get(0);
    }
}