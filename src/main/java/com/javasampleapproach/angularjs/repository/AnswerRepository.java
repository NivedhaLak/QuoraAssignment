package com.javasampleapproach.angularjs.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javasampleapproach.angularjs.dao.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
