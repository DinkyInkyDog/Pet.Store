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
	
	
	public PetStoreData(PetStore ps) {
		petStoreId = ps.getPetStoreId();
		petStoreName = ps.getPetStoreName();
		petStoreAddress= ps.getPetStoreAddress();
		petStoreCity = ps.getPetStoreCity();
		petStoreState = ps.getPetStoreState();
		petStoreZip = ps.getPetStoreZip();
		petStorePhone = ps.getPetStorePhone();
		
		for(Employee employee: ps.getEmployees()) {
			employeeResponse.add(new EmployeeResponse(employee));
		}
		
		for(Customer customer : ps.getCustomers()) {
			customerResponse.add(new CustomerResponse(customer));
		}
	}
	
	
	
	
	@Data
	@NoArgsConstructor
	static class EmployeeResponse {
		private Long employeeId;
		
		private String employeeFirstName;
		private String employeeLastName;
		
		private String employeePhone;
		private String employeeJobTitle;
		
		
		public EmployeeResponse(Employee em) {
			employeeId = em.getEmployeeId();
			employeeFirstName = em.getEmployeeFirstName();
			employeeLastName = em.getEmployeeLastName();
			employeePhone = em.getEmployeePhone();
			employeeJobTitle = em.getEmployeeJobTitle();
			
				}
		
	}
	
	@Data
	static class CustomerResponse {
		private Long customerId;
		
		private String customerFirstName;
		private String customerLastName;
		private String customerEmail;
		
		
		
		public CustomerResponse(Customer cs) {
			customerId = cs.getCustomerId();
			customerFirstName = cs.getCustomerFirstName();
			customerLastName = cs.getCustomerLastName();
			customerEmail = cs.getCustomerEmail();
			
			
		}
	}
}
