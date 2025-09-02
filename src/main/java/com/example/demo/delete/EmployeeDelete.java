package com.example.demo.delete;

import java.sql.Timestamp;

public class EmployeeDelete {
	private int employee_id;
	private String employee_name;
	private int age;
	private String passwords;
	private Timestamp start_date;
	private Timestamp end_date;

	public EmployeeDelete(){};
	
	public EmployeeDelete(int employee_id, String employee_name, int age, String passwords, Timestamp start_date, Timestamp end_date) {
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.age = age;
		this.passwords = passwords;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public int getId() {
		return employee_id;
	}

	public void setId(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getName() {
		return employee_name;
	}

	public void setName(String employee_name) {
		this.employee_name = employee_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPasswords() {
		return passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	public Timestamp getStart_date() {
		return start_date;
	}

	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	public Timestamp getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}
	
}
