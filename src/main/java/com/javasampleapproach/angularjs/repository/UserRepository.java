package com.javasampleapproach.angularjs.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javasampleapproach.angularjs.dao.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
