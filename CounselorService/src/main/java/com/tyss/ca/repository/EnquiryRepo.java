package com.tyss.ca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.ca.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {

}
