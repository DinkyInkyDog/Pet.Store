package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CustomerData {
	private Long customerId;
	
	private String customerFirstName;
	private String customerLastName;
	private String customerEmail;
	
	
	private Set<PetStoreResponse> storesShoppedAtResponse = new HashSet<>();
}
