package com.example.demo.mapper;

import java.time.LocalDate;
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
				    @Param("startDateFrom") LocalDate startDateFrom,
				    @Param("startDateTo") LocalDate startDateTo,
				    @Param("endDateFrom") LocalDate endDateFrom,
				    @Param("endDateTo") LocalDate endDateTo
				);

	@Select("SELECT employee_id AS employeeId, " +
			"employee_name AS employeeName, " +
			"age, " +
			"passwords, " +
			"start_date AS startDate, " +
			"end_date AS endDate " +
			"FROM employee WHERE employee_id = #{id}")
	Employee findById(Integer id);
}


//package com.example.demo.mapper;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//
//import com.example.demo.entity.Employee;
//
//@Mapper
//public interface EmployeeMapper {
//	List<Employee> searchEmployees(
//			@Param("employeeId") Integer employeeId,
//			@Param("employeeName") String employeeName,
//			@Param("ageFrom") Integer ageFrom,
//			@Param("ageTo") Integer ageTo,
//			@Param("startDateFrom") LocalDate sFrom,
//			@Param("startDateTo") LocalDate sTo,
//			@Param("endDateFrom") LocalDate eFrom,
//			@Param("endDateTo") LocalDate eTo);
//
//	@Select("SELECT employee_id AS employeeId, " +
//			"employee_name AS employeeName, " +
//			"age, " +
//			"passwords, " +
//			"start_date AS startDate, " +
//			"end_date AS endDate " +
//			"FROM employee WHERE employee_id = #{id}")
//	Employee findById(Integer id);
//}
