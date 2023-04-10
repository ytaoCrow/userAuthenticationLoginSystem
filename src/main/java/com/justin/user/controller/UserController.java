package com.justin.user.controller;

import com.justin.user.dao.UserDao;
import com.justin.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public User getUserById(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDao.addUser(user);
        return user;
    }

}
