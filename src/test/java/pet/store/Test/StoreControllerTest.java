package pet.store.Test;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pet.store.controller.model.EmployeeData;
import pet.store.service.StoreService;

class StoreControllerTest {

	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	@AfterEach
	public void tearDown() {
        
    }

	@Test
	void test() {
		StoreService ss = new StoreService();
		EmployeeData employeeMock = new EmployeeData();
		employeeMock.setEmployeeFirstName("Lauren");
		employeeMock.setEmployeeLastName("Andrews");
		employeeMock.setEmployeePhone("(612)295-7893");
		employeeMock.setEmployeeJobTitle("General Stocker");
		
		
		//EmployeeData reEm = ss.saveEmployee(employeeMock);
	}

}
