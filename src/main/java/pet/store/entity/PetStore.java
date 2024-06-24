package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;


@Data
public class PetStore {
	
	private int pet_store_id;
	private String pet_store_name;
	private String pet_store_address;
	private String pet_store_city;
	private String pet_store_state;
	private int pet_store_zip;
	private int pet_store_phone;
	
	private Set<Employee> employees = new HashSet<>();
	private Set<Customer> customers = new HashSet<>();
}
