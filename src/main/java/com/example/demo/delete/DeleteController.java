package com.example.demo.delete;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteController {
	@Autowired
	private DeleteService ds;
	@Autowired
	private HttpSession session;
	
	private void setSessionInfo(Model m) {
		String loginName = (String) session.getAttribute("loginName");
		LocalDateTime loginTime = (LocalDateTime) session.getAttribute("loginTime");
		m.addAttribute("loginName", loginName);
		m.addAttribute("loginTime", loginTime);
	}
	
	
	@GetMapping("/menu")
	public String showMenu() {
		return "menu";
	}
	
	@RequestMapping("/deleteForm")
	public String showDeleteForm(Model m) {
		setSessionInfo(m);
		return "deleteForm";
	}
	
	@PostMapping("/delete")
	public String showDelete(Model m,
											@RequestParam("employee_id")List<String> employee_ids
											) {
		setSessionInfo(m);
		if(employee_ids == null || employee_ids.isEmpty()) {
			m.addAttribute("msg", "IDが入力されていません");
			return "deleteForm";
		}
		
		Integer loginId = (Integer) session.getAttribute("loginId");
		List<Integer> ids = new ArrayList<>();
		
		for(String id : employee_ids) {
			if(id == null || id.trim().isEmpty()) {
				continue;
			}
				
		
			try {
				int employeeId = Integer.parseInt(id);
				EmployeeDelete employee = ds.findById(employeeId);
				
				if(loginId == employeeId) {
					m.addAttribute("msg", "このIDは削除できません");
					return "deleteForm";
				}
				
				if(employee == null) {
					m.addAttribute("msg", "IDが存在していません");
					return "deleteForm";
				}
				
				ids.add(employeeId);
				
			}catch(NumberFormatException e) {
				m.addAttribute("msg", "数字で入力してください");
				return "deleteForm";
			}
		}
		m.addAttribute("ids", ids);
		return "delete";
	}
	
	@PostMapping("/deleteResult")
	public String showResult(Model m,
											@RequestParam("ids") List<Integer> ids) {
		for(Integer employeeIds : ids) {
		ds.delete(employeeIds);
		}
		m.addAttribute("msg", "削除が完了しました");
		return "deleteResult";
	}
}
