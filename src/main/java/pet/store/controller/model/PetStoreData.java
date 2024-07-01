package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;


@Data
@NoArgsConstructor
public class PetStoreData {
	private Long petStoreId;
	
	private String petStoreName;
	private String petStoreAddress;
	private String petStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;
	
	private Set<EmployeeResponse> employeeResponse = new HashSet<>();
	
	private Set<CustomerResponse> customerResponse = new HashSet<>();
	
	@Data
	@NoArgsConstructor
	static class EmployeeResponse {
		private Long employeeId;
		
		private String employeeFirstName;
		private String employeeLastName;
		
		private String employeePhone;
		private String employeeJobTitle;
		
		private Long petStoreId;
		
		public EmployeeResponse(Employee em) {
			employeeId = em.getEmployeeId();
			employeeFirstName = em.getEmployeeFirstName();
			employeeLastName = em.getEmployeeLastName();
			employeePhone = em.getEmployeePhone();
			employeeJobTitle = em.getEmployeeJobTitle();
			
			PetStore ps = em.getPetStore();
			
			petStoreId = ps.getPetStoreId();		}
		
	}
	
	@Data
	static class CustomerResponse {
		private Long customerId;
		
		private String customerFirstName;
		private String customerLastName;
		private String customerEmail;
		
		private Set<Long> petStores = new HashSet<>();
		
		public CustomerResponse(Customer cs) {
			customerId = cs.getCustomerId();
			customerFirstName = cs.getCustomerFirstName();
			customerLastName = cs.getCustomerLastName();
			customerEmail = cs.getCustomerEmail();
			
			for (PetStore ps : cs.getStoresShoppedAt()) {
				petStores.add(ps.getPetStoreId());
			}
		}
	}
}
