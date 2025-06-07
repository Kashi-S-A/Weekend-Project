package com.tyss.ca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.ca.entity.Counselor;

public interface CounselorRepo extends JpaRepository<Counselor, Integer> {

	Optional<Counselor> findByEmail(String email);
	
	Optional<Counselor> findByEmailAndPassword(String email, String password);
}
