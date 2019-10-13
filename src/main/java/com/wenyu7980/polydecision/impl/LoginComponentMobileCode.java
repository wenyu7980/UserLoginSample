package com.wenyu7980.polydecision.impl;

import com.wenyu7980.decisiontable.Decision;
import com.wenyu7980.domain.LoginUser;
import com.wenyu7980.domain.User;
import com.wenyu7980.polydecision.LoginComponent;
import com.wenyu7980.service.CodeService;
import com.wenyu7980.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.wenyu7980.decisiontable.DecisionCompare.NOT_NULL;

@Component
public class LoginComponentMobileCode implements LoginComponent {

    @Autowired
    private UserService userService;
    @Autowired
    private CodeService codeService;

    @Override
    public User login(LoginUser user) {
        // 手机号+验证码
        User user1 = userService.findByMobile(user.getMobile());
        if (codeService.codeCheck(user.getCode())) {
            return user1.setMethod(User.LoginMethod.MOBILE_CODE);
        }
        throw new RuntimeException("验证码不正确");
    }

    @Override
    public Decision mobile() {
        return NOT_NULL();
    }

    @Override
    public Decision code() {
        return NOT_NULL();
    }
}
