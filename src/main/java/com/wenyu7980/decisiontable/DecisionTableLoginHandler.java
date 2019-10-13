package com.wenyu7980.decisiontable;

import com.wenyu7980.LoginHandler;
import com.wenyu7980.domain.LoginUser;
import com.wenyu7980.domain.User;
import com.wenyu7980.service.CodeService;
import com.wenyu7980.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.function.Function;

import static com.wenyu7980.decisiontable.DecisionCompare.*;

public class DecisionTableLoginHandler implements LoginHandler {
    private final DecisionTable<Function<LoginUser, User>> DECISIONS = DecisionTable
            .of();

    @Autowired
    private UserService userService;
    @Autowired
    private CodeService codeService;

    public DecisionTableLoginHandler() {
        // 用户名 手机号 密码 验证码
        DECISIONS.add(this::userPasswd, NOT_NULL(), ANY(), NOT_NULL(), ANY());
        DECISIONS.add(this::userCode, NOT_NULL(), NULL(), NULL(), NOT_NULL());
        DECISIONS
                .add(this::mobilePasswd, NULL(), NOT_NULL(), NOT_NULL(), ANY());
        DECISIONS.add(this::mobileCode, NULL(), NOT_NULL(), NULL(), NOT_NULL());
    }

    @Override
    public User login(LoginUser user) {
        Function<LoginUser, User> function = DECISIONS
                .get(user.getUsername(), user.getMobile(), user.getPassword(),
                        user.getCode())
                .orElseThrow(() -> new RuntimeException("组合不正确"));
        return function.apply(user);
    }

    private User userPasswd(LoginUser user) {
        // 用户名+密码
        User user1 = userService.findByUsername(user.getUsername());
        if (Objects.equals(user1.getPassword(), user.getPassword())) {
            return user1;
        }
        throw new RuntimeException("密码不正确");
    }

    private User userCode(LoginUser user) {
        // 用户名+验证码
        User user1 = userService.findByUsername(user.getUsername());
        if (codeService.codeCheck(user.getCode())) {
            return user1;
        }
        throw new RuntimeException("验证码不正确");
    }

    private User mobilePasswd(LoginUser user) {
        // 手机号+密码
        User user1 = userService.findByMobile(user.getMobile());
        if (Objects.equals(user1.getPassword(), user.getPassword())) {
            return user1;
        }
        throw new RuntimeException("密码不正确");
    }

    private User mobileCode(LoginUser user) {
        // 手机号+验证码
        User user1 = userService.findByMobile(user.getMobile());
        if (codeService.codeCheck(user.getCode())) {
            return user1;
        }
        throw new RuntimeException("验证码不正确");
    }
}
