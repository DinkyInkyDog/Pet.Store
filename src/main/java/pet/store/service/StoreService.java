package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.Dao.EmployeeDao;
import pet.store.Dao.PetStoreDao;
import pet.store.controller.model.EmployeeData;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
public class StoreService {
	
	@Autowired
	private EmployeeDao emDao;
	private PetStoreDao psDao;
	
	@Transactional(readOnly = false)
	public EmployeeData insertEmployee(EmployeeData ed) {
		Long employeeId = ed.getEmployeeId();
		Employee employee = findOrCreateEmployee(employeeId);
		setFeildsInEmployee(employee, ed);
		return new EmployeeData (emDao.save(employee));
	}

	private void setFeildsInEmployee(Employee employee, EmployeeData ed) {
		employee.setEmployeeFirstName(ed.getEmployeeFirstName());
		employee.setEmployeeLastName(ed.getEmployeeLastName());
		employee.setEmployeeJobTitle(ed.getEmployeeJobTitle());
		employee.setEmployeePhone(ed.getEmployeePhone());
		Long petId = ed.getPetStoreId();
		
		if (petId != null) {
		PetStore petStore = psDao.findById(petId).orElseThrow(
				() -> new NoSuchElementException("Pet Store with Id=" + petId + " was not Found"));
		employee.setPetStore(petStore);
		} else {
			System.out.println("No pet stores");
		}
	}

	private Employee findOrCreateEmployee(Long employeeId) {
		Employee em;
		if (Objects.isNull(employeeId)) {
			em = new Employee();
		} else {
			em = findEmployeeById(employeeId);
		}
		return em;
	}

	private Employee findEmployeeById(Long employeeId) {
		return emDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException(
				"Employee with ID =" + employeeId + " was not found"));
	}

}
