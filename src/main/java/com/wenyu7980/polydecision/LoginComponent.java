package com.wenyu7980.polydecision;

import com.wenyu7980.decisiontable.Decision;
import com.wenyu7980.domain.LoginUser;
import com.wenyu7980.domain.User;

import static com.wenyu7980.decisiontable.DecisionCompare.NULL;

public interface LoginComponent {
    User login(LoginUser user);

    default Decision username() {
        return NULL();
    }

    default Decision password() {
        return NULL();
    }

    default Decision mobile() {
        return NULL();
    }

    default Decision code() {
        return NULL();
    }
}
