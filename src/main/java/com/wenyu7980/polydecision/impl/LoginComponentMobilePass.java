package com.wenyu7980.polydecision.impl;

import com.wenyu7980.decisiontable.Decision;
import com.wenyu7980.domain.LoginUser;
import com.wenyu7980.domain.User;
import com.wenyu7980.polydecision.LoginComponent;
import com.wenyu7980.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.wenyu7980.decisiontable.DecisionCompare.ANY;
import static com.wenyu7980.decisiontable.DecisionCompare.NOT_NULL;

@Component
public class LoginComponentMobilePass implements LoginComponent {
    @Autowired
    private UserService userService;

    @Override
    public User login(LoginUser user) {
        // 手机号+密码
        User user1 = userService.findByMobile(user.getMobile());
        if (Objects.equals(user1.getPassword(), user.getPassword())) {
            return user1;
        }
        throw new RuntimeException("密码不正确");
    }

    @Override
    public Decision password() {
        return NOT_NULL();
    }

    @Override
    public Decision mobile() {
        return NOT_NULL();
    }

    @Override
    public Decision code() {
        return ANY();
    }
}
