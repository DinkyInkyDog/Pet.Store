package pet.store.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.CustomerData;
import pet.store.controller.model.EmployeeData;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreResponse;
import pet.store.service.StoreService;


@RestController
@RequestMapping("/pet_store")
@Slf4j
public class StoreController {
	@Autowired
	private StoreService ss = new StoreService();
	
	//Employee---------------------------
	
	
	@PostMapping("/store/{storeId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmployeeData saveEmployee(
			@PathVariable Long storeId,
			@RequestBody EmployeeData employeeData) {
		PetStoreResponse psr = new PetStoreResponse();
		psr.setPetStoreId(storeId);
		employeeData.setPetStoreEmployment(psr);
		log.info("Creating employee {} for pet store with Id={}", employeeData, storeId);
		return ss.saveEmployee(employeeData);
	}
	
	/**
	 * 
	 * @param employeeId- The id of the existing employee row
	 * @param employeeData- the request body you wrote with your put request
	 * make sure you include all the information. Whatever you put it to is what it'll be
	 * that includes nulls. If you only change the ones you want and leave the others blank
	 * it'll change them to null.
	 * @return
	 */
	@PutMapping("/store/employee/{employeeId}")
	public EmployeeData selectEmployeeFromId(
			@PathVariable Long employeeId,
			@RequestBody EmployeeData employeeData) {
		employeeData.setEmployeeId(employeeId);
		log.info("Update Employee {}", employeeData);
		return ss.saveEmployee(employeeData);
	}
	
	
	
	//PetStore---------------------
	
	
	@PostMapping("/store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertPetStore(
			@RequestBody PetStoreData storeData) {
		log.info("Creating Pet store {}", storeData);
		return ss.savePetStore(storeData);
	}
	
	
	@PutMapping("/store/{storeId}")
	public PetStoreData updatePetStore(
			@RequestBody PetStoreData storeData,
			@PathVariable Long storeId) {
		storeData.setPetStoreId(storeId);
		log.info("Updating Pet store {}", storeData);
		return ss.savePetStore(storeData);
	}
	
	
	//Customer------------------------
	
	@PostMapping("/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CustomerData saveCustomer(
			@RequestBody CustomerData customerData) {
		log.info("Creating Customer {}", customerData);
		return ss.saveCustomer(customerData);
	}
	
	
	@PutMapping("/customer/{customerId}")
	public CustomerData updateCustomer(
			@PathVariable Long customerId,
			@RequestBody CustomerData customerData) {
		customerData.setCustomerId(customerId);
		log.info("Updating customer {}", customerData);
		return ss.saveCustomer(customerData);
	}
	
	
	
	
}
