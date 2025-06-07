package com.tyss.ca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.ca.repository.EnquiryRepo;

@Service
public class EnquiryService {

	@Autowired
	private EnquiryRepo enquiryRepo;
}
