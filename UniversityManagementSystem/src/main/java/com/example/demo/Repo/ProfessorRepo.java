package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Professor;
import java.util.List;


public interface ProfessorRepo extends JpaRepository<Professor, Integer>{

	List<Professor> findByDepartment(String department);
}
