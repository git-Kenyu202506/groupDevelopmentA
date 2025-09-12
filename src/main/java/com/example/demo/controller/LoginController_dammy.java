package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController_dammy {

    @GetMapping("/login")
    public String loginPage() {
        return "login_dammy"; 
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, HttpSession session) {
        session.setAttribute("loginName", username);

        String loginTime = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        session.setAttribute("loginTime", loginTime);

        return "redirect:/employees/menu"; 
    }
}