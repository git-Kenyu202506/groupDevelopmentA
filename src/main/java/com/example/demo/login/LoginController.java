package com.example.demo.login;

import java.time.LocalDateTime;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@Autowired
	private LoginService ls;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/login")
	public String getAllEmployee() {
		return "login";
	}

	@PostMapping("/menu")
	public String enu(Model m,
									@RequestParam("employee_name") String employee_name,
									@RequestParam("passwords") String passwords) {
		Login loginUser = ls.login(employee_name, passwords);
		if(loginUser != null) {
			session.setAttribute("loginId", loginUser.getId());
			session.setAttribute("loginName", loginUser.getName());
			session.setAttribute("loginTime", LocalDateTime.now());
			m.addAttribute("employee_name", loginUser.getName());
			m.addAttribute("loginTime",  LocalDateTime.now());
			return "menu";
		}else {
			m.addAttribute("msg", "名前またはパスワードが間違っています");
			return "login";
		}
	}
}
