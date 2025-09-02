package com.example.demo.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
	@Autowired
	private DeleteMapper dm;
	
	public EmployeeDelete findById(int employee_id){
		return dm.findById(employee_id);
}
	public void delete(int employee_id) {
		dm.delete(employee_id);
	}
}
