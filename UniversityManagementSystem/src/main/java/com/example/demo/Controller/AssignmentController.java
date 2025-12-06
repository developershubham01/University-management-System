package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.AssignmentDto;
import com.example.demo.Entity.Assignment;
import com.example.demo.Service.AssignmentService;

@RestController
public class AssignmentController {

	@Autowired
	AssignmentService assignmentService;

	@PostMapping("addassignment/{courseId}")
	public ResponseEntity<String> addAssignment(@RequestBody AssignmentDto assignmentDto,@PathVariable Integer courseId) {
		assignmentService.addAssignment(assignmentDto,courseId);
		return new ResponseEntity<String>("assignment added", HttpStatus.CREATED);
	}

	@GetMapping("getassignment/{id}")
	public ResponseEntity<Assignment> getAssignement(@PathVariable Integer id) {
		Assignment assignment = assignmentService.getAssignment(id);
		return new ResponseEntity<Assignment>(assignment, HttpStatus.OK);
	}

}
