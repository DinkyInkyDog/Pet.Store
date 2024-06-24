package pet.store.entity;

import lombok.Data;

@Data
public class Employee {
	private int employee_id;
	private String employee_first_name;
	private String employee_last_name;
	private int employee_phone;
	private String employee_job_title;
	
	private PetStore petstore;
}
