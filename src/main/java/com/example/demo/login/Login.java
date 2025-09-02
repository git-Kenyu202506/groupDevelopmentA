package com.example.demo.login;

public class Login {
	private int employee_id;
	private String employee_name;
	private String passwords;
	
	public Login() {}
	
	public Login(int employee_id, String employee_name, String passwords) {
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.passwords = passwords;
	}

	public String getName() {
		return employee_name;
	}

	public void setName(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getPasswords() {
		return passwords;
	}

	public void setPassword(String passwords) {
		this.passwords = passwords;
	}
	
	public int getId() {
		return employee_id;
	}

	public void setId(int employee_id) {
		this.employee_id = employee_id;
	}
}
