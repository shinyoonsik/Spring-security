package com.example.springsecurity.controller;

import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.repository.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller // View를 리턴하겠다
public class IndexController {

    // 사용할 template engine: mustache
    // mustache기본 폴더: src/main/resources/
    // 뷰리졸버 설정: templates(prefix), .mustache(suffix)

    private final UserEntityRepository userEntityRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public IndexController(UserEntityRepository userEntityRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @GetMapping({"", "/"})
    public String index() {
        return "index"; // src/main/resources/templates/index.mustache
    }

    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String amdin() {
        return "admin";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager() {
        return "manager";
    }

    @GetMapping("/loginForm")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(UserEntity user) {
        log.info("requestBody: {}", user);

        // 패스워드가 암호화가 되지않으면 security로 로그인할 수 없음
        user.setRole("ROLE_USER");
        String password = user.getPassword();
        String encryptPwd = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptPwd);
        userEntityRepository.save(user);

        return "redirect:/loginForm";
    }

    @GetMapping("/join/complete")
    @ResponseBody
    public String joinComplete() {
        return "회원가입 완료";
    }

}
