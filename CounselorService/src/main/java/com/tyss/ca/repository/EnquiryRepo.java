package com.tyss.ca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.ca.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {

	Page<Enquiry> findByCounselorId(Integer counselorId, Pageable pageable);
}
