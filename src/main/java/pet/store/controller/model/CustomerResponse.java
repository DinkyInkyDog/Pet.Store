package pet.store.controller.model;

import lombok.Data;
import pet.store.entity.Customer;

@Data
public class CustomerResponse {
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