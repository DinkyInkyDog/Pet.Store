package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.Dao.EmployeeDao;
import pet.store.controller.model.EmployeeData;
import pet.store.entity.Employee;

@Service
public class StoreService {
	
	@Autowired
	private EmployeeDao emDao;
	
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
		employee.setEmployeejobTitle(ed.getEmployeejobTitle());
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
		return emDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException(
				"Employee with ID =" + employeeId + " was not found"));
	}

}
