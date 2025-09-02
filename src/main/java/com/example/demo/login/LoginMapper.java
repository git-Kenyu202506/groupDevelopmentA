package com.example.demo.login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
	@Select("SELECT * FROM employee WHERE employee_name = #{employee_name} AND passwords = #{passwords}")
	Login selectNameAndPassword(String employee_name, String passwords);
}
