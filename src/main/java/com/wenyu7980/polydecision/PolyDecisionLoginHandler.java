package com.wenyu7980.polydecision;

import com.wenyu7980.LoginHandler;
import com.wenyu7980.decisiontable.DecisionTable;
import com.wenyu7980.domain.LoginUser;
import com.wenyu7980.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PolyDecisionLoginHandler implements LoginHandler {

    private final DecisionTable<LoginComponent> DECISIONS = DecisionTable.of();

    @Autowired
    public PolyDecisionLoginHandler(List<LoginComponent> componentList) {
        for (LoginComponent component : componentList) {
            DECISIONS.add(component, component.username(), component.mobile(),
                    component.password(), component.code());
        }
    }

    @Override
    public User login(LoginUser user) {
        return DECISIONS
                .get(user.getUsername(), user.getMobile(), user.getPassword(),
                        user.getCode())
                .orElseThrow(() -> new RuntimeException("组合不正确")).login(user);
    }
}
