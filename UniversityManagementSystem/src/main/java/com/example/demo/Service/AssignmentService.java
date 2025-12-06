package com.example.demo.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AssignmentDto;
import com.example.demo.Entity.Assignment;
import com.example.demo.Entity.Course;
import com.example.demo.Exception.AssignmentServiceException;
import com.example.demo.Repo.AssignmentRepo;
import com.example.demo.Repo.CourseRepo;
import com.example.demo.Repo.SubmissionRepo;

@Service
public class AssignmentService {

	@Autowired
	AssignmentRepo assignmentRepo;

	@Autowired
	SubmissionRepo submissionRepo;

	@Autowired
	CourseRepo courseRepo;

	private static final Logger logger = LoggerFactory.getLogger(AssignmentService.class);

	public void addAssignment(AssignmentDto assignmentDto, Integer courseId) {
		logger.info("adding assignment data");
		if (assignmentDto == null) {
			logger.error("assignment dto is null");
			throw new AssignmentServiceException("assignment data must not be null", HttpStatus.BAD_REQUEST);
		}
		
		if(assignmentDto.getAssignmentTitle()==null||assignmentDto.getAssignmentTitle().trim().isEmpty())
		{
			logger.error("assignment title is null");
			throw new AssignmentServiceException("assignment title must not be null", HttpStatus.BAD_REQUEST);
		}

		Assignment assignment = new Assignment();
		assignment.setAssignmentTitle(assignmentDto.getAssignmentTitle());

		Course course = courseRepo.findById(courseId)
				.orElseThrow(() -> new RuntimeException("course is not found with id:" + courseId));
		assignment.setCourse(course);
		assignmentRepo.save(assignment);
		logger.info("assignment data added successfully");

	}

	public Assignment getAssignment(int id) {
		logger.info("finding assignment by id");
		Optional<Assignment> o = assignmentRepo.findById(id);
		if (o.isEmpty()) {
			logger.error("assignment id not found",id);
			throw new AssignmentServiceException(" assignment id not found", HttpStatus.NOT_FOUND);
		}
		logger.info("assignment found by id:" + id);
		return o.get();
	}

}
