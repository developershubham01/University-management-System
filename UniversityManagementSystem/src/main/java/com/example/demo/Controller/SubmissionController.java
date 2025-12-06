package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.SubmissionDto;
import com.example.demo.Entity.Submission;
import com.example.demo.Service.SubmissionService;

@RestController
public class SubmissionController {

	@Autowired
	SubmissionService submissionService;

	@PostMapping("addsubmission/{assignmentId}/{studentId}")
	public ResponseEntity<String> addSubmission(@RequestBody SubmissionDto submissionDto,@PathVariable Integer assignmentId, @PathVariable Integer studentId) {
		submissionService.addSubmission(submissionDto, assignmentId, studentId);
		return new ResponseEntity<String>("submission added", HttpStatus.CREATED);
	}

	@GetMapping("getsubmission/{id}")
	public ResponseEntity<Submission> getSubmission(@PathVariable Integer id) {
		Submission submission = submissionService.getSubmission(id);
		return new ResponseEntity<Submission>(submission, HttpStatus.OK);
	}
}
