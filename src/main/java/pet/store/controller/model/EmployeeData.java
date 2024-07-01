package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.controller.model.PetStoreData.CustomerResponse;
import pet.store.controller.model.PetStoreData.EmployeeResponse;

@Data
@NoArgsConstructor
public class EmployeeData {
	private Long employeeId;

	private String employeeFirstName;
	private String employeeLastName;

	private String employeePhone;
	private String employeejobTitle;
	
	private PetStoreResponse petResponse;
	
	
	@Data
	static class PetStoreResponse {
		private Long petStoreId;

		private String petStoreName;
		private String petStoreAddress;
		private String petStoreCity;
		private String petStoreState;
		private String petStoreZip;
		private String petStorePhone;
		
		private Set<EmployeeResponse> employeeResponse = new HashSet<>();
		private Set<CustomerResponse> customerResponse = new HashSet<>();
	}
}

