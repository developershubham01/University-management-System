package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.StudentDto;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.StudentServiceException;
import com.example.demo.Repo.StudentRepo;

@Service
public class StudentService {

	@Autowired
	StudentRepo studentRepo;

	private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

	public void addStudent(StudentDto studentDto) {

		if (studentDto == null) {
			logger.error("studentdto is null");
			throw new StudentServiceException("student data musst not be null", HttpStatus.BAD_REQUEST);
		}
		if(studentDto.getName()==null||studentDto.getName().trim().isEmpty())
		{
			logger.error("student name is null");
			throw new StudentServiceException("student name must not be null", HttpStatus.BAD_REQUEST);
		}
		if(studentDto.getEmail()==null||studentDto.getEmail().trim().isEmpty())
		{
			logger.error("student email is null");
			throw new StudentServiceException("student email must not be null", HttpStatus.BAD_REQUEST);
		}
		Student student = new Student();
		student.setEmail(studentDto.getEmail());
		student.setName(studentDto.getName());
		studentRepo.save(student);
		logger.info("student data saved successfully");

	}

	public Student getStudent(int id) {
		logger.info("getting student by id:" + id);
		Optional<Student> o = studentRepo.findById(id);
		if (o.isEmpty()) {
			logger.error("student is not found with id",id);
			throw new StudentServiceException("student id not found", HttpStatus.NOT_FOUND);
		}
		logger.info("student found by id:" + id);
		return o.get();
	}

	public List<Student> getStudent(String name) {
		logger.info("finding student by name:" + name);
		if (name == null || name.trim().isEmpty()) {
			throw new StudentServiceException("name must not be empty", HttpStatus.BAD_REQUEST);
		}
		logger.info("found student by name:" + name);
		return studentRepo.findByName(name);
	}

}
