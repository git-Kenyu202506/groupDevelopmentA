package com.example.demo.delete;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DeleteMapper {

	@Select("SELECT employee_id FROM employee WHERE employee_id = #{employee_id}")
	EmployeeDelete findById(int employee_id);
	
	@Delete("DELETE FROM employee WHERE employee_id = #{employee_id}")
	void delete(int employee_id);
}
