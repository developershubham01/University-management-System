package com.example.demo.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.SubmissionDto;
import com.example.demo.Entity.Assignment;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.Submission;
import com.example.demo.Exception.SubmissionServiceException;
import com.example.demo.Repo.AssignmentRepo;
import com.example.demo.Repo.StudentRepo;
import com.example.demo.Repo.SubmissionRepo;

@Service
public class SubmissionService {

	@Autowired
	SubmissionRepo submissionRepo;

	@Autowired
	AssignmentRepo assignmentRepo;

	@Autowired
	StudentRepo studentRepo;

	private static final Logger logger = LoggerFactory.getLogger(SubmissionService.class);

	public void addSubmission(SubmissionDto submissionDto, Integer assignmentId, Integer studentId) {
		logger.info("submission data is adding");

		if (submissionDto == null) {
			logger.error("submission dto is null");
			throw new SubmissionServiceException("submission data must not be null", HttpStatus.BAD_REQUEST);
		}
		
		if(submissionDto.getGrade()==null||submissionDto.getGrade().trim().isEmpty())
		{
			logger.error("grade is null");
			throw new SubmissionServiceException("grade must not be null", HttpStatus.BAD_REQUEST);
		}
		Submission submission = new Submission();
		submission.setGrade(submissionDto.getGrade());

		Assignment assignment = assignmentRepo.findById(assignmentId)
				.orElseThrow(() -> new RuntimeException("assignment not found with id:" + assignmentId));
		submission.setAssignment(assignment);

		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new RuntimeException("student not found with id:" + studentId));
		submission.setStudent(student);
		submissionRepo.save(submission);

		logger.info("submission data added successfully");
	}

	public Submission getSubmission(int id) {
		logger.info("getting submission by id:" + id);
		Optional<Submission> o = submissionRepo.findById(id);
		if (o.isEmpty()) {
			logger.error("submission is not found with id", id);
			throw new SubmissionServiceException("submission id not found", HttpStatus.NOT_FOUND);
		}
		logger.info("found submission by id:" + id);
		return o.get();
	}

}
