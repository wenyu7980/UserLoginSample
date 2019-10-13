package com.wenyu7980;

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
    public void loginHandler() {
        Assert.assertEquals(3, this.loginHandlerMap.size());
    }

}
