package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.ProfessorDto;
import com.example.demo.Entity.Professor;
import com.example.demo.Exception.ProfessorServiceException;
import com.example.demo.Repo.CourseRepo;
import com.example.demo.Repo.ProfessorRepo;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepo professorRepo;

	@Autowired
	CourseRepo courseRepo;

	private static final Logger logger = LoggerFactory.getLogger(ProfessorService.class);

	public void addProfessor(ProfessorDto professorDto) {

		logger.info("professor data is adding");

		if (professorDto == null) {
			logger.error("professor dto is null");
			throw new ProfessorServiceException("professor dto must not be null", HttpStatus.BAD_REQUEST);
		}
		if (professorDto.getDepartment() == null||professorDto.getDepartment().trim().isEmpty()) {
			logger.error("department is null");
			throw new ProfessorServiceException("department must not be null", HttpStatus.BAD_REQUEST);
		}

		if (professorDto.getName() == null||professorDto.getName().trim().isEmpty()) {
			logger.error("professor name is null");
			throw new ProfessorServiceException("professor name must not be null", HttpStatus.BAD_REQUEST);
		}
		Professor professor = new Professor();
		professor.setDepartment(professorDto.getDepartment());
		professor.setName(professorDto.getName());
		professorRepo.save(professor);
		logger.info("professor data added successfully");

	}

	public Professor getProfessor(int id) {

		logger.info("finding professor by id:" + id);
		Optional<Professor> o = professorRepo.findById(id);
		if (o.isEmpty()) {
			logger.error("professor id is not found", id);
			throw new ProfessorServiceException("Professor id not found", HttpStatus.NOT_FOUND);
		}
		logger.info("professor found by id:" + id);
		return o.get();
	}

	public List<Professor> getProfessorByDepartment(String department) {

		logger.info("finding professor by department:" + department);
		if (department == null) {
			throw new ProfessorServiceException("department must not be null", HttpStatus.BAD_REQUEST);
		}
		logger.info("found professor by department:" + department);
		return professorRepo.findByDepartment(department);
	}

}
