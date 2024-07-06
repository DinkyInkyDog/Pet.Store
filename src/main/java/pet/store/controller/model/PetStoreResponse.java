package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
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
		
		
	public PetStoreResponse(PetStore ps) {
			petStoreId = ps.getPetStoreId();
			petStoreName = ps.getPetStoreName();
			petStoreAddress = ps.getPetStoreAddress();
			petStoreCity = ps.getPetStoreCity();
			petStoreState = ps.getPetStoreState();
			petStoreZip = ps.getPetStoreZip();
			petStorePhone = ps.getPetStorePhone();
			
		}

}