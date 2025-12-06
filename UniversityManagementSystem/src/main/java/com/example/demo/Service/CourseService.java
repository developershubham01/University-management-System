package com.example.demo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.CourseDto;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.Professor;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.CourseServiceException;
import com.example.demo.Repo.AssignmentRepo;
import com.example.demo.Repo.CourseRepo;
import com.example.demo.Repo.ProfessorRepo;
import com.example.demo.Repo.StudentRepo;

@Service
public class CourseService {

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	AssignmentRepo assignmentRepo;

	@Autowired
	ProfessorRepo professorRepo;
	
	private static final Logger logger=LoggerFactory.getLogger(CourseService.class);

	public void addCourse(CourseDto courseDto, Integer professorId) {
		
		logger.info("adding course data");
		if(courseDto==null)
		{
			logger.error("course dto is null");
			throw new CourseServiceException("course data must not be null", HttpStatus.BAD_REQUEST);
		}
		if(courseDto.getTitle()==null||courseDto.getTitle().trim().isEmpty())
		{
			logger.error("course title is null");
			throw new CourseServiceException("course title must not be null", HttpStatus.BAD_REQUEST);
		}
		
		if(courseDto.getCredit()<=0)
		{
			logger.error("invalid course credit");
			throw new CourseServiceException("course credit must be greater than 0", HttpStatus.BAD_REQUEST);
		}
		Course course = new Course();
		course.setCredit(courseDto.getCredit());
		course.setTitle(courseDto.getTitle());

		Professor professor = professorRepo.findById(professorId)
				.orElseThrow(() -> new RuntimeException("professor not found with id:" + professorId));
		course.setProfessor(professor);
		courseRepo.save(course);
		
		logger.info("course data added successfully");

	}

	public void assignStudent(int studentId, int courseId) {
		
		Student student = studentRepo.findById(studentId).get();
		Course course = courseRepo.findById(courseId).get();

		student.getCourse().add(course);
		studentRepo.save(student);
	}

}
