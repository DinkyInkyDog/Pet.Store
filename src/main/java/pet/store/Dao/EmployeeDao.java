package pet.store.Dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
	Set<Employee> findByForeignKey(Long foreignKey);
}
