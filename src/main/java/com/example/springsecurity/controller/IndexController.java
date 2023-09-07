package com.example.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // View를 리턴하겠다
public class IndexController {

    // 사용할 template engine: mustache
    // mustache기본 폴더: src/main/resources/
    // 뷰리졸버 설정: templates(prefix), .mustache(suffix)


    @GetMapping({"","/"})
    public String index(){
        return "index"; // src/main/resources/templates/index.mustache
    }

}
