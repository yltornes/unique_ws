package net.uniquewholesale.unique.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.uniquewholesale.unique.catalog.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Long> {

}
