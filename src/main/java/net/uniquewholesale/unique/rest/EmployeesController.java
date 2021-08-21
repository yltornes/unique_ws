package net.uniquewholesale.unique.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.uniquewholesale.unique.catalog.Employee;
import net.uniquewholesale.unique.catalog.Status;
import net.uniquewholesale.unique.dao.EmployeeDAO;
import net.uniquewholesale.unique.dao.StatusDAO;

@RestController
@Api(tags = {"Employees Information"})
@RequestMapping("/employees")
public class EmployeesController implements EmployeesOperations {
	
	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private StatusDAO statusDAO;


	public ResponseEntity< List<Employee> > getEmployee() {
		
		List<Employee> employees = employeeDAO.findAll();
		
		return ResponseEntity.ok(employees);
	}
	
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeID") Long productId) {
		
		Optional<Employee> optionalEmployee = employeeDAO.findById(productId);
		if(optionalEmployee.isPresent()) {
			return ResponseEntity.ok(optionalEmployee.get());
		} else {
//			return ResponseEntity.noContent().build();
			return ResponseEntity.notFound().build();
		}
		
	}
	

	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		try {
			Employee newEmployee = employeeDAO.save(employee);
			return ResponseEntity.ok(newEmployee);
		} catch ( Exception e) {
			System.out.println("Error: " + e.getMessage());
			//return ResponseEntity.unprocessableEntity().body(employee);
			return new ResponseEntity("Used",HttpStatus.IM_USED);   
		}
		
	}
	

	public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeID") Long productId) {
		try {
			employeeDAO.deleteById(productId);
			return ResponseEntity.ok(null);
		} catch ( Exception e) { 
			return ResponseEntity.notFound().build();   
		}
		
	}
	

	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		try {
			Optional<Employee> optionalEmployee = employeeDAO.findById(employee.getId());
			if(optionalEmployee.isPresent()) {
				Employee updateEmployee = employeeDAO.save(employee);
				return ResponseEntity.ok(updateEmployee);
			} else {
				return ResponseEntity.notFound().build(); 
			}
		} catch ( Exception e) {
			System.out.println("Error: " + e.getMessage());
			//return ResponseEntity.unprocessableEntity().body(employee);
			return ResponseEntity.unprocessableEntity().body(employee);
		}
		
	} 
	
}
