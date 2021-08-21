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


@Api(tags = {"Employees Information"})
@RequestMapping("/employees")
public interface EmployeesOperations {
	
	@ApiOperation(value = "Fetch all Employees ", notes = "Notes for your controller", response = Status.class)
	
	@RequestMapping(value="", method=RequestMethod.GET)
	
	public ResponseEntity< List<Employee> > getEmployee();
	
	@ApiResponses(value = { 
	        @ApiResponse(code = 400, message = "Invalid employee id supplied"),   
	        @ApiResponse(code = 404, message = "Employee not found") 
	})
	
	@RequestMapping(value="{employeeID}", method=RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeID") Long productId);
	
	@ApiOperation(value = "Create Employee.", notes = "Notes for your controller", response = Employee.class)
	@ApiParam(value = "First Name")
	@ApiResponses(value = { 
	        @ApiResponse(code = 400, message =  
	        "Employee already exists."), 
	        @ApiResponse(code = 404, message = "Employee data is missing.") 
	})
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee);
	
	@DeleteMapping(value="{employeeID}") // /employees
	public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeID") Long productId);
	
	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee);
	
}
