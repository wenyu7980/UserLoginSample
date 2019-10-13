package com.wenyu7980;

import com.wenyu7980.domain.LoginUser;
import com.wenyu7980.domain.User;

public interface LoginHandler {
    User login(LoginUser user);
}
