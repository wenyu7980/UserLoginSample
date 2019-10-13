package com.wenyu7980.service;

import org.springframework.stereotype.Component;

@Component
public class CodeService {

    public boolean codeCheck(String code) {
        return "8888".equals(code);
    }
}
