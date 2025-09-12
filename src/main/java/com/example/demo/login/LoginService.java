package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	@Autowired
	private LoginMapper lm;
	
	public Login login(int employee_id, String passwords) {
		return lm.selectNameAndPassword(employee_id, passwords);
	}
}