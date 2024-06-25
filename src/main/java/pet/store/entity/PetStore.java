package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class PetStore {
	
	private int pet_store_id;
	private String pet_store_name;
	private String pet_store_address;
	private String pet_store_city;
	private String pet_store_state;
	private int pet_store_zip;
	private int pet_store_phone;
	
	@OneToMany(mappedBy = "employees", cascade = cascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Employee> employees = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Customer> customers = new HashSet<>();
}
