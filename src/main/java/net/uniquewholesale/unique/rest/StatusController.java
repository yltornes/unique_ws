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

import io.swagger.annotations.ApiOperation;
import net.uniquewholesale.unique.catalog.Status;
import net.uniquewholesale.unique.dao.StatusDAO;

@RestController
@RequestMapping("/status")
public class StatusController {
	
	@Autowired
	private StatusDAO statusDAO;

	@ApiOperation(value = "Hello greeting to the Status ", notes = "Notes for your controller", response = Status.class)
	@RequestMapping(value="hello", method=RequestMethod.GET)
	public ResponseEntity<Status> getHello(){
		Status status = new Status();
		status.setId(1L);
		status.setDescription("Active");
		return ResponseEntity.ok(status); 
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity< List<Status> > getStatus() {
		
		List<Status> status = statusDAO.findAll();
		
		return ResponseEntity.ok(status);
	}
	
	@RequestMapping(value="{statusID}", method=RequestMethod.GET)
	@ApiOperation(value = "Fecth the status by id", notes = "Provide the id in the param, for e.g status/3", response = Status.class)
	public ResponseEntity<Status> getStatusById(@PathVariable("statusID") Long statusId) {
		
		Optional<Status> optionalStatus = statusDAO.findById(statusId);
		if(optionalStatus.isPresent()) {
			return ResponseEntity.ok(optionalStatus.get());
		} else {
//			return ResponseEntity.noContent().build();
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Status> createStatus(@RequestBody Status status) {
		try {
			System.out.println(" Status creation");
			Status newStatus = statusDAO.save(status);
			System.out.println("OK");
			return ResponseEntity.ok(newStatus);
		} catch ( Exception e) {
			System.out.println("Error: " + e.getMessage());
			//return ResponseEntity.unprocessableEntity().body(status);
			return new ResponseEntity("Used",HttpStatus.IM_USED);   
		}
		
	}
	
	@DeleteMapping(value="{statusID}") // /status
	public ResponseEntity<Void> deleteStatus(@PathVariable("statusID") Long statusId) {
		try {
			statusDAO.deleteById(statusId);
			return ResponseEntity.ok(null);
		} catch ( Exception e) { 
			return ResponseEntity.notFound().build();   
		}
		
	}
	
	@PutMapping
	public ResponseEntity<Status> updateStatus(@RequestBody Status status) {
		try {
			Optional<Status> optionalStatus = statusDAO.findById(status.getId());
			if(optionalStatus.isPresent()) {
				Status updateStatus = statusDAO.save(status);
				return ResponseEntity.ok(updateStatus);
			} else {
				return ResponseEntity.notFound().build(); 
			}
		} catch ( Exception e) {
			System.out.println("Error: " + e.getMessage());
			//return ResponseEntity.unprocessableEntity().body(status);
			return ResponseEntity.unprocessableEntity().body(status);
		}
		
	} 
	
}
