package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.Dao.EmployeeDao;
import pet.store.Dao.PetStoreDao;
import pet.store.controller.model.EmployeeData;
import pet.store.controller.model.PetStoreData;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
public class StoreService {
	
	@Autowired
	private EmployeeDao emDao;
	
	@Autowired
	private PetStoreDao psDao;
	
	
	
	
	
	@Transactional(readOnly = false)
	public EmployeeData saveEmployee(Long storeId, EmployeeData ed) {
		Long employeeId = ed.getEmployeeId();
		PetStore store = findPetStoreById(storeId);
		
		Employee employee = findOrCreateEmployee(employeeId);
		setFeildsInEmployee(employee, ed);
		employee.setPetStore(store);
		
		
		return new EmployeeData (emDao.save(employee));
	}

	private void setFeildsInEmployee(Employee employee, EmployeeData ed) {
		employee.setEmployeeFirstName(ed.getEmployeeFirstName());
		employee.setEmployeeLastName(ed.getEmployeeLastName());
		employee.setEmployeeJobTitle(ed.getEmployeeJobTitle());
		employee.setEmployeePhone(ed.getEmployeePhone());
		
		
		
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
	
	
	
	@Transactional(readOnly = false)
	public EmployeeData updateEmployee(Long employeeId, EmployeeData employeeData) {
		Employee employee = findOrCreateEmployee(employeeId);
		
		return null;
	}

	
	//PetStore-----------
	
	
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData storedata) {
		Long storeId = storedata.getPetStoreId();
		PetStore store = findOrCreatePetStore(storeId);
		setFeildsInStore(storedata, store);
		
		return new PetStoreData (psDao.save(store));
	}

	private void setFeildsInStore(PetStoreData storedata, PetStore store) {
		store.setPetStoreName(storedata.getPetStoreName());
		store.setPetStoreAddress(storedata.getPetStoreAddress());
		store.setPetStoreCity(storedata.getPetStoreCity());
		store.setPetStoreState(storedata.getPetStoreState());
		store.setPetStoreZip(storedata.getPetStoreZip());
		store.setPetStorePhone(storedata.getPetStorePhone());
		
	}




	private PetStore findPetStoreById(Long petStoreId) {
		return psDao.findById(petStoreId).orElseThrow(
				() -> new NoSuchElementException(
						"Pet store with ID=" + petStoreId + " was not found"));
	}
	
	

}
