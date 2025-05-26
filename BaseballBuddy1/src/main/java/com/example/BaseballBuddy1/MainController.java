package com.example.BaseballBuddy1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // 메인 페이지
    @GetMapping("/")
    public String home() {
        return "index";  // index.html을 렌더링
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "login";  // login.html을 렌더링
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signup() {
        return "signup";  // signup.html을 렌더링
    }

    // 직관 게시판 페이지
    @GetMapping("/posts/1")
    public String posts1() {
        return "posts";  // posts.html을 렌더링
    }

    // 자리 게시판 페이지
    @GetMapping("/posts/2")
    public String posts2() {
        return "posts";  // posts.html을 렌더링
    }

    // 나눔 게시판 페이지
    @GetMapping("/posts/3")
    public String posts3() {
        return "posts";  // posts.html을 렌더링
    }
}
