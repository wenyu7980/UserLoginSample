package com.wenyu7980;

import com.wenyu7980.domain.LoginUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserloginHanlderTests {

    @Autowired
    private Map<String, LoginHandler> loginHandlerMap;

    @Test
    public void loginHandlerUserPasswd() {
        LoginUser user = new LoginUser("username", "passwd", null, null);
        for (Map.Entry<String, LoginHandler> entry : loginHandlerMap
                .entrySet()) {
            Assert.assertEquals("用户名密码" + entry.getKey(), user.getUsername(),
                    entry.getValue().login(user).getUsername());
        }
    }

    @Test
    public void loginHandlerUserCode() {
        LoginUser user = new LoginUser("username", null, null, "8888");
        for (Map.Entry<String, LoginHandler> entry : loginHandlerMap
                .entrySet()) {
            Assert.assertEquals("用户名密码" + entry.getKey(), user.getUsername(),
                    entry.getValue().login(user).getUsername());
        }
    }

    @Test
    public void loginHandlerMobilePass() {
        LoginUser user = new LoginUser(null, "passwd", "18812341234", null);
        for (Map.Entry<String, LoginHandler> entry : loginHandlerMap
                .entrySet()) {
            Assert.assertEquals("用户名密码" + entry.getKey(), user.getMobile(),
                    entry.getValue().login(user).getMobile());
        }
    }

    @Test
    public void loginHandlerMobileCode() {
        LoginUser user = new LoginUser(null, null, "18812341234", "8888");
        for (Map.Entry<String, LoginHandler> entry : loginHandlerMap
                .entrySet()) {
            Assert.assertEquals("用户名密码" + entry.getKey(), user.getMobile(),
                    entry.getValue().login(user).getMobile());
        }
    }
}
