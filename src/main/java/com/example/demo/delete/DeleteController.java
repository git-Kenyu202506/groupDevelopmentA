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
											@RequestParam(value = "employee_id", required = false)List<String> employee_ids,
											@RequestParam("returnPage") String returnPage
											) {
		setSessionInfo(m);
		
		if(employee_ids == null) {
			employee_ids = new ArrayList<>();
		}
		
		if(employee_ids == null || employee_ids.isEmpty()) {
			m.addAttribute("msg", "IDが入力されていません");
			m.addAttribute("employee_ids", "");
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
					m.addAttribute("employee_ids", employee_ids.get(0));
					return "deleteForm";
				}
				
				if(employee == null) {
					m.addAttribute("msg", "IDが存在していません");
					m.addAttribute("employee_ids", employee_ids.get(0));
					return "deleteForm";
				}
				
				ids.add(employeeId);
				
			}catch(NumberFormatException e) {
				m.addAttribute("msg", "数字で入力してください");
				m.addAttribute("employee_ids", employee_ids.get(0));
				return "deleteForm";
			}
		}
		m.addAttribute("ids", ids);
		m.addAttribute("employee_ids", employee_ids);
		m.addAttribute("returnPage", returnPage);
		return "delete";
	}
	
	@PostMapping("/back")
	public String showBack(Model m,
									@RequestParam(value = "employee_id", required = false)List<String> employee_ids,
									@RequestParam("returnPage") String returnPage
									) {
		setSessionInfo(m);

		if("deleteForm".equals(returnPage)) {
			m.addAttribute("returnPage", "deleteForm");
			m.addAttribute("employee_ids", employee_ids.get(0));
			return "deleteForm";
		}else {
			m.addAttribute("returnPage", "testDeleteForm");
			return "testDeleteForm";
		}
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
