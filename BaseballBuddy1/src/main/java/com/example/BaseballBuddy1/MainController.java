package com.example.BaseballBuddy1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";    }

    @GetMapping("/members/login")
    public String login() {
        return "redirect:/login.html";    }

    @GetMapping("/registration")
    public String registration() {
        return "redirect:/registration.html";
    }
    @GetMapping("/myPage")
    public String myPage() {
        return "redirect:/myPage.html";    }

    @GetMapping("/attendCalendar")
    public String attendCalendar() {
        return "redirect:/attendCalendar.html";
    }
    @GetMapping("/attendRecommendQuestion")
    public String attendRecommendQuestion() {
        return "redirect:/attendRecommendQuestion.html";
    }
    @GetMapping("/attendRecommendResult")
    public String attendRecommendResult() {
        return "redirect:/attendRecommendResult.html";
    }

    //추천결과
    //댓글?
    @GetMapping("/review")
    public String review() {
        return "redirect:/review.html";
    }
    @GetMapping("/reviewPost")
    public String reviewPost() {
        return "redirect:/reviewPost.html";
    }

}
