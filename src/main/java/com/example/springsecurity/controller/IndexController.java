package com.example.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // View를 리턴하겠다
public class IndexController {

    // 사용할 template engine: mustache
    // mustache기본 폴더: src/main/resources/
    // 뷰리졸버 설정: templates(prefix), .mustache(suffix)

    @GetMapping({"","/"})
    public String index(){
        return "index"; // src/main/resources/templates/index.mustache
    }

    @GetMapping("/user")
    @ResponseBody
    public String user(){
        return "user";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String amdin(){
        return "admin";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager(){
        return "manager";
    }

    @GetMapping("/login")
    @ResponseBody
    public String login(){
        return "login";
    }


    @GetMapping("/join")
    @ResponseBody
    public String join(){
        return "join";
    }

    @GetMapping("/join/complete")
    @ResponseBody
    public String joinComplete(){
        return "회원가입 완료";
    }

}
