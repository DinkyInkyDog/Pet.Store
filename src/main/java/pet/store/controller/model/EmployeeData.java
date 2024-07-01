package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.controller.model.PetStoreData.CustomerResponse;
import pet.store.controller.model.PetStoreData.EmployeeResponse;
import pet.store.entity.Employee;

@Data
@NoArgsConstructor
public class EmployeeData {
	


	private Long employeeId;

	private String employeeFirstName;
	private String employeeLastName;

	private String employeePhone;
	private String employeeJobTitle;
	
	private PetStoreResponse petResponse;
	
	public EmployeeData(Employee em) {
		employeeId = em.getEmployeeId();
		employeeFirstName = em.getEmployeeFirstName();
		employeeLastName = em.getEmployeeLastName();
		employeePhone = em.getEmployeePhone();
		employeeJobTitle = em.getEmployeejobTitle();
		
		PetStoreResponse psr = new PetStoreResponse(em);
	}
	
	@Data
	@NoArgsConstructor
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
		
		public PetStoreResponse(Employee em) {
			petStoreId = em.getPetStore().getPetStoreId();
			petStoreAddress = em.getPetStore().getPetStoreAddress();
			petStoreCity = em.getPetStore().getPetStoreCity();
			petStoreState = em.getPetStore().getPetStoreState();
			petStoreZip = em.getPetStore().getPetStoreZip();
			petStorePhone = em.getPetStore().getPetStoreZip();
			
			for (employeeR)
		}
	}
}

