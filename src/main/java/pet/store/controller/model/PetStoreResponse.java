package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.controller.model.PetStoreData.CustomerResponse;
import pet.store.controller.model.PetStoreData.EmployeeResponse;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreResponse {
	
	private Long petStoreId;

	private String petStoreName;
	private String petStoreAddress;
	private String petStoreCity;
	private String petStoreState;
	private String petStoreZip;
	private String petStorePhone;
		
	private Set<Long> employeeId = new HashSet<>();
	private Set<Long> customerId = new HashSet<>();
		
	public PetStoreResponse(PetStore ps) {
			petStoreId = ps.getPetStoreId();
			petStoreAddress = ps.getPetStoreAddress();
			petStoreCity = ps.getPetStoreCity();
			petStoreState = ps.getPetStoreState();
			petStoreZip = ps.getPetStoreZip();
			petStorePhone = ps.getPetStorePhone();
			
			for (Employee em : ps.getEmployees()) {
				employeeId.add(em.getEmployeeId());
			}
			
			for (Customer ct : ps.getCustomers()) {
				customerId.add(ct.getCustomerId());
			}
		}

}