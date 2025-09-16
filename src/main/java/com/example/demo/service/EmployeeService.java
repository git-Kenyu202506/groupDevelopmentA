package com.example.demo.service;

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

    public List<Employee> searchEmployees(Integer employeeId, String employeeName, Integer ageFrom, Integer ageTo,
                                          String startDateFrom, String startDateTo, String endDateFrom, String endDateTo) {
        return mapper.searchEmployees(employeeId, employeeName, ageFrom, ageTo, startDateFrom, startDateTo, endDateFrom, endDateTo);
    }
    public Employee findById(Integer id) {
        return mapper.findById(id);
    }
   
}


