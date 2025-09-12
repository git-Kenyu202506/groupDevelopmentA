package com.example.demo.login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
	@Select("SELECT * FROM employee WHERE employee_id = #{employee_id} AND passwords = #{passwords}")
	Login selectNameAndPassword(int employee_id, String passwords);
}
