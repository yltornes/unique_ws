package net.uniquewholesale.unique.rest;

import java.util.List;

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

@RestController
@RequestMapping("/status")
public interface StatusOperation {
	
	@ApiOperation(value = "Hello greeting to the Status ", notes = "Notes for your controller", response = Status.class)
	@RequestMapping(value="hello", method=RequestMethod.GET)
	public ResponseEntity<Status> getHello();
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity< List<Status> > getStatus();
	
	@RequestMapping(value="{statusID}", method=RequestMethod.GET)
	@ApiOperation(value = "Fecth the status by id", notes = "Provide the id in the param, for e.g status/3", response = Status.class)
	public ResponseEntity<Status> getStatusById(@PathVariable("statusID") Long statusId);
	
	@PostMapping
	public ResponseEntity<Status> createStatus(@RequestBody Status status);

	@PutMapping
	public ResponseEntity<Status> updateStatus(@RequestBody Status status);
	
	@DeleteMapping(value="{statusID}") // /status
	@RequestMapping()
	public ResponseEntity<Void> deleteStatus(@PathVariable("statusID") Long statusId);
		
}
