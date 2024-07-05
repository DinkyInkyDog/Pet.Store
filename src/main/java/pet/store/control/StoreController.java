package pet.store.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.EmployeeData;
import pet.store.controller.model.PetStoreData;
import pet.store.service.StoreService;


@RestController
@RequestMapping("/pet_store")
@Slf4j
public class StoreController {
	@Autowired
	private StoreService ss = new StoreService();
	
	
	@PostMapping("/store/{storeId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmployeeData saveEmployee(
			@PathVariable Long storeId,
			@RequestBody EmployeeData employeeData) {
		log.info("Creating employee {} for pet store with Id={}", employeeData, storeId);
		return ss.saveEmployee(employeeData);
	}
	
	@PutMapping("/store/employee/{employeeId}")
	public EmployeeData selectEmployeeFromId(
			@PathVariable Long employeeId,
			@RequestBody EmployeeData employeeData) {
		log.info("View Employee with Id= {}", employeeId);
		return ss.updateEmployee(employeeId, employeeData);
	}
	
	
	
	//PetStore---------------------
	@PostMapping("/store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData savePetStore(
			@RequestBody PetStoreData storeData) {
		log.info("Creating Pet store");
		return ss.savePetStore(storeData);
	}
	
}
