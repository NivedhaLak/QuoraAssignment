package com.javasampleapproach.angularjs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javasampleapproach.angularjs.dao.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	Question findById(Long id);
}
