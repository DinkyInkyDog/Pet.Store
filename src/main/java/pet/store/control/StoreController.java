package pet.store.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.EmployeeData;
import pet.store.service.StoreService;


@RestController
@RequestMapping("/pet_store")
@Slf4j
public class StoreController {
	@Autowired
	private StoreService ss = new StoreService();
	
	
	@PostMapping("/employee")
	public EmployeeData insertEmployee(
			@RequestBody EmployeeData employeeData) {
		log.info("Creating employee {}", employeeData);
		return ss.insertEmployee(employeeData);
	}
}
