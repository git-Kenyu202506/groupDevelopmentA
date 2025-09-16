package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Employee;

@Mapper
public interface EmployeeMapper {
    List<Employee> searchEmployees(
        @Param("employeeId") Integer employeeId,
        @Param("employeeName") String employeeName,
        @Param("ageFrom") Integer ageFrom,
        @Param("ageTo") Integer ageTo,
        @Param("startDateFrom") String startDateFrom,
        @Param("startDateTo") String startDateTo,
        @Param("endDateFrom") String endDateFrom,
        @Param("endDateTo") String endDateTo
    );

    @Select("SELECT employee_id AS employeeId, " +
            "employee_name AS employeeName, " +
            "age, " +
            "passwords, " +
            "DATE_FORMAT(start_date, '%Y%m%d') AS startDate, " +
            "DATE_FORMAT(end_date, '%Y%m%d') AS endDate " +
            "FROM employee WHERE employee_id = #{id}")
    Employee findById(Integer id);
	
}

