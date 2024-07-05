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
	
	@Autowired
	private PetStoreDao psDao;
	
	
	
	
	
	@Transactional(readOnly = false)
	public EmployeeData saveEmployee(EmployeeData ed) {
		Long employeeId = ed.getEmployeeId();
		Employee employee = findOrCreateEmployee(employeeId);
		setFeildsInEmployee(employee, ed);
		//does that actually save it? check?
		
		return new EmployeeData (emDao.save(employee));
	}

	private void setFeildsInEmployee(Employee employee, EmployeeData ed) {
		employee.setEmployeeFirstName(ed.getEmployeeFirstName());
		employee.setEmployeeLastName(ed.getEmployeeLastName());
		employee.setEmployeeJobTitle(ed.getEmployeeJobTitle());
		employee.setEmployeePhone(ed.getEmployeePhone());
		
		if (ed.getPetStoreEmployment().getPetStoreId() != null) {
			Long petStoreId = ed.getPetStoreEmployment().getPetStoreId();
			PetStore petStore = findOrCreatePetStore(petStoreId);
			employee.setPetStore(petStore);
		}
		
	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		PetStore ps;
		if (Objects.isNull(petStoreId)) {
			ps = new PetStore();
		} else {
			ps = findPetStoreById(petStoreId);
		}
		return ps;
	}

	private PetStore findPetStoreById(Long petStoreId) {
		return psDao.findById(petStoreId).orElseThrow(
				() -> new NoSuchElementException(
						"Pet store with ID=" + petStoreId + " was not found"));
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
		return emDao.findById(employeeId).orElseThrow(
				() -> new NoSuchElementException(
				"Employee with ID =" + employeeId + " was not found"));
	}
	
	
	
	@Transactional(readOnly = true)
	public EmployeeData updateEmployee(Long employeeId) {
		Employee employee = findOrCreateEmployee(employeeId);
		EmployeeData foundEmployee = new EmployeeData(employee);
		return foundEmployee;
	}

}
