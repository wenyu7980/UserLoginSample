package com.wenyu7980.service;

import com.wenyu7980.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    public User findByUsername(String username) {
        return new User("username", "passwd", "18812341234");
    }

    public User findByMobile(String moible) {
        return new User("username", "passwd", "18812341234");
    }

}
