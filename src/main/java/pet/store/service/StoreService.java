package pet.store.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.Dao.CustomerDao;
import pet.store.Dao.EmployeeDao;
import pet.store.Dao.PetStoreDao;
import pet.store.control.StoreController.entity;
import pet.store.controller.model.CustomerData;
import pet.store.controller.model.CustomerResponse;
import pet.store.controller.model.EmployeeData;
import pet.store.controller.model.EmployeeResponse;
import pet.store.controller.model.EntityIdCollection;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreResponse;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
public class StoreService {
	
	@Autowired
	private EmployeeDao emDao;
	
	@Autowired
	private PetStoreDao psDao;
	
	@Autowired
	private CustomerDao cuDao;
	
	
	
	@Transactional(readOnly = false)
	public EmployeeData saveEmployee(EmployeeData ed) {
		Long employeeId = ed.getEmployeeId();
		PetStore petstore = null;

		Employee employee = findOrCreateEmployee(employeeId);
		if (ed.getPetStoreEmployment() != null) {
			Long petStoreId = ed.getPetStoreEmployment().getPetStoreId();
			petstore = findPetStoreById(petStoreId);
			employee.setPetStore(petstore);
		}
		
		setFeildsInEmployee(employee, ed);
		
		
		
		return new EmployeeData (emDao.save(employee));
	}

	private void setFeildsInEmployee(Employee employee, EmployeeData ed) {
		employee.setEmployeeFirstName(ed.getEmployeeFirstName());
		employee.setEmployeeLastName(ed.getEmployeeLastName());
		employee.setEmployeeJobTitle(ed.getEmployeeJobTitle());
		employee.setEmployeePhone(ed.getEmployeePhone());
		
		
		
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
	public EmployeeData updateEmployee(EmployeeData employeeData) {
		
		
		return null;
	}

	
	//PetStore-----------------------------------------
	
	
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
		
		if (!storedata.getCustomerResponse().isEmpty()) {
			for(CustomerResponse cr: storedata.getCustomerResponse()) {
				if(cr.getCustomerId() != null) {
					Long customerId = cr.getCustomerId();
					Customer customer = cuDao.findById(customerId).orElseThrow(
							() -> new NoSuchElementException("Customer with ID=" +
					customerId + "was not found"));
					customer.getStoresShoppedAt().add(store);
					store.getCustomers().add(customer);
					
				} else {
					Customer customer = new Customer();
					customer.setCustomerFirstName(cr.getCustomerFirstName());
					customer.setCustomerEmail(cr.getCustomerEmail());
					customer.setCustomerLastName(cr.getCustomerLastName());
					customer.getStoresShoppedAt().add(store);
					store.getCustomers().add(customer);
				}
			}
		}
		if(!storedata.getEmployeeResponse().isEmpty()) {
			for(EmployeeResponse er: storedata.getEmployeeResponse()) {
				if(er.getEmployeeId() != null) {
					Long employeeId = er.getEmployeeId();
					Employee employee = emDao.findById(employeeId).orElseThrow(
							() -> new NoSuchElementException("employee with ID=" + 
					employeeId + " was not found"));
					employee.setPetStore(store);
					store.getEmployees().add(employee);
					
				} else {
					Employee employee = new Employee();
					employee.setEmployeeFirstName(er.getEmployeeFirstName());
					employee.setEmployeeLastName(er.getEmployeeLastName());
					employee.setEmployeeJobTitle(er.getEmployeeJobTitle());
					employee.setEmployeePhone(er.getEmployeePhone());
					employee.setPetStore(store);
					store.getEmployees().add(employee);
				}
			}
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

	
	
	//Customer------------------------------
	
	@Transactional(readOnly = false)
	public CustomerData saveCustomer(CustomerData customerData) {
		Long customerId = customerData.getCustomerId();
		Customer customer = findOrCreateCustomer(customerId);
		setFeildsInCustomer(customer, customerData);
		
		return new CustomerData(cuDao.save(customer));
	}

	private void setFeildsInCustomer(Customer customer, CustomerData cd) {
		customer.setCustomerEmail(cd.getCustomerEmail());
		customer.setCustomerFirstName(cd.getCustomerFirstName());
		customer.setCustomerLastName(cd.getCustomerLastName());
		
		if (!cd.getStoresShoppedAtResponse().isEmpty()) {
			for (PetStoreResponse store : cd.getStoresShoppedAtResponse()) {
				if(store.getPetStoreId() != null) {
					Long storeId = store.getPetStoreId();
					PetStore petStore = psDao.findById(storeId).orElseThrow(
							() -> new NoSuchElementException("Pet store with ID = "+
							storeId + " was not found"));
					petStore.getCustomers().add(customer);
					customer.getStoresShoppedAt().add(petStore);
				} else {
					PetStore petStore = new PetStore();
					petStore.setPetStoreAddress(store.getPetStoreAddress());
					petStore.setPetStoreCity(store.getPetStoreCity());
					petStore.setPetStoreName(store.getPetStoreName());
					petStore.setPetStorePhone(store.getPetStorePhone());
					petStore.setPetStoreState(store.getPetStoreState());
					petStore.setPetStoreZip(store.getPetStoreZip());
					petStore.getCustomers().add(customer);
					customer.getStoresShoppedAt().add(petStore);
				}
			}
		}
		
	}

	private Customer findOrCreateCustomer(Long customerId) {
		Customer cu;
		if (Objects.isNull(customerId)) {
			cu = new Customer();
		} else {
			cu = findCustomerById(customerId);
		}
		return cu;
	}

	private Customer findCustomerById(Long customerId) {
		return cuDao.findById(customerId).orElseThrow(() -> new NoSuchElementException(
				"Customer with ID =" + customerId + " was not Found"));
	}


	public void deleteById(Long id, entity entity) {
		switch (entity) {
		case EMPLOYEE: 
			if (emDao.existsById(id)) {
			emDao.deleteById(id);
			} else {
				throw new NoSuchElementException("There is no Employee with ID=" +id);
			}
			break;
		case PET_STORE:
			if (psDao.existsById(id)) {
				psDao.deleteById(id);
				} else {
					throw new NoSuchElementException("There is no Pet Store with ID=" +id);
				}
			break;
		case CUSTOMER:
			if (cuDao.existsById(id)) {
				cuDao.deleteById(id);
				} else {
					throw new NoSuchElementException("There is no Customer with ID=" +id);
				}
			break;
		}
		
	}

	
	@Transactional(readOnly = true)
	public Object retrieveById(EntityIdCollection object, entity entity) {
		switch(entity) {
		case EMPLOYEE:
			Long storeId = object.getId1();
			Long employeeId = object.getId2();
			Employee employee = emDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException(
					"Employee with ID ="+ employeeId + " was not found."));
			if (employee.getPetStore().getPetStoreId() != storeId) {
				throw new IllegalStateException("Employee with store ID = " + storeId + " and employee ID = "+ employeeId + " was not found.");
			}
			return new EmployeeData(employee);
			
		case CUSTOMER:
			Long customerId = object.getId1();
			Customer customer = cuDao.findById(customerId).orElseThrow(() -> new NoSuchElementException(
					"Customer with ID=" +customerId + " was not found."));
			return new CustomerData(customer);
		case PET_STORE:
			Long petStoreId = object.getId1();
			PetStore store = psDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException(
					"Pet store wih ID=" + petStoreId + " was not found."));
			return new PetStoreData(store);		
		default:
			throw new IllegalArgumentException("No Entity specified.");
				
			
		}
	}


	@Transactional(readOnly = true)
	public Object retrieveAll(entity entity) {
		switch(entity) {
		case EMPLOYEE:
			List<Employee> employees = (List<Employee>) emDao.findAll();
			Set<EmployeeData> employeesData = new HashSet<>();
			for(Employee employee: employees) {
				employeesData.add(new EmployeeData(employee));
			}
			if(employeesData.isEmpty()) {
				throw new IllegalArgumentException("There are no employees to show.");
			}
			return employeesData;
		case CUSTOMER:
			List<Customer> customers = (List<Customer>) cuDao.findAll();
			Set<CustomerData> customersData = new HashSet<>();
			for (Customer customer: customers) {
				customersData.add(new CustomerData(customer));
			}
			if(customersData.isEmpty()) {
				throw new IllegalArgumentException("There are no customers to show.");
			}
			return customersData;
		case PET_STORE:
			List<PetStore> petStores = (List<PetStore>) psDao.findAll();
			Set<PetStoreData> petStoresData = new HashSet<>();
			for(PetStore petstore : petStores) {
				petStoresData.add(new PetStoreData(petstore));
			}
			if(petStoresData.isEmpty()) {
				throw new IllegalArgumentException("There are no pet stores to show.");
			}
			return petStoresData;
		default:
			throw new IllegalArgumentException("No entity specified");
		}
	}

	


	
	

}
