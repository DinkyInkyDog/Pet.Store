package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	private int customer_id;
	private String customer_first_name;
	private String customer_last_name;
	private String customer_email;
	
	private Set<PetStore> storesShoppedAt = new HashSet<>();
}
