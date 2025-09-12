package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;

@Service
public class EmployeeService {
	private final EmployeeMapper mapper;

	public EmployeeService(EmployeeMapper mapper) {
		this.mapper = mapper;
	}

	public List<Employee> searchEmployees(
			Integer employeeId,
			String employeeName,
			Integer ageFrom,
			Integer ageTo,
			LocalDate sFrom,
			LocalDate sTo,
			LocalDate eFrom,
			LocalDate eTo) {
		return mapper.searchEmployees(employeeId, employeeName, ageFrom, ageTo,
				sFrom, sTo, eFrom, eTo);
	}

	public Employee findById(Integer id) {
		return mapper.findById(id);
	}
}
