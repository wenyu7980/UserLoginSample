package com.wenyu7980.ifmethod;

import com.wenyu7980.LoginHandler;
import com.wenyu7980.domain.LoginUser;
import com.wenyu7980.domain.User;
import com.wenyu7980.service.CodeService;
import com.wenyu7980.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class IfLoginHandler implements LoginHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private CodeService codeService;

    @Override
    public User login(LoginUser user) {
        if (Objects.nonNull(user.getUsername()) && Objects
                .nonNull(user.getPassword())) {
            // 用户名+密码
            User user1 = userService.findByUsername(user.getUsername());
            if (Objects.equals(user1.getPassword(), user.getPassword())) {
                return user1;
            }
            throw new RuntimeException("密码不正确");
        } else if (Objects.nonNull(user.getUsername()) && Objects
                .nonNull(user.getCode())) {
            // 用户名+验证码
            User user1 = userService.findByUsername(user.getUsername());
            if (codeService.codeCheck(user.getCode())) {
                return user1;
            }
            throw new RuntimeException("验证码不正确");
        } else if (Objects.nonNull(user.getMobile()) && Objects
                .nonNull(user.getPassword())) {
            // 手机号+密码
            User user1 = userService.findByMobile(user.getMobile());
            if (Objects.equals(user1.getPassword(), user.getPassword())) {
                return user1;
            }
            throw new RuntimeException("密码不正确");
        } else if (Objects.nonNull(user.getUsername()) && Objects
                .nonNull(user.getCode())) {
            // 手机号+验证码
            User user1 = userService.findByMobile(user.getMobile());
            if (codeService.codeCheck(user.getCode())) {
                return user1;
            }
            throw new RuntimeException("验证码不正确");
        } else {
            throw new RuntimeException("组合不正确");
        }
    }
}
