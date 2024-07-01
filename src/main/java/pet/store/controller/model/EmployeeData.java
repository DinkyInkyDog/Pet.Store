package pet.store.controller.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import pet.store.entity.Employee;



@Data
@NoArgsConstructor
public class EmployeeData {
	


	private Long employeeId;

	private String employeeFirstName;
	private String employeeLastName;

	private String employeePhone;
	private String employeeJobTitle;
	
	private Long petStoreId;
	
	public EmployeeData(Employee em) {
		employeeId = em.getEmployeeId();
		employeeFirstName = em.getEmployeeFirstName();
		employeeLastName = em.getEmployeeLastName();
		employeePhone = em.getEmployeePhone();
		employeeJobTitle = em.getEmployeeJobTitle();


		petStoreId = em.getPetStore().getPetStoreId();
	}
	
	
}

