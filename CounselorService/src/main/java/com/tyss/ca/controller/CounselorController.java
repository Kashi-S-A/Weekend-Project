package com.tyss.ca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.ca.dto.CouselorDTO;
import com.tyss.ca.dto.LoginDTO;
import com.tyss.ca.entity.Enquiry;
import com.tyss.ca.service.CounselorService;

import jakarta.validation.Valid;

/**
 * CounselorController handles requests related to counselors. It provides
 * endpoints for registering, logging in, updating, deleting counselors, and
 * retrieving inquiries associated with counselors.
 */

@RestController
@RequestMapping("/counselor")
public class CounselorController {

	@Autowired
	private CounselorService counselorService;

	// Add methods to handle requests related to counselors

	// registerCounselor

	/**
	 * Registers a new counselor.
	 * 
	 * @param counselorDto the DTO containing counselor details
	 * @return ResponseEntity with success message or CounselorNotFoundException
	 *         will be thrown if the counselor already exists
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerCounselor(@Valid @RequestBody CouselorDTO counselorDto) {
		return counselorService.registerCounselor(counselorDto);
	}

	/**
	 * Logs in a counselor.
	 * 
	 * @param loginDTO the DTO containing login credentials
	 * 
	 * @return ResponseEntity with success message or error message if login fails
	 */
	@PostMapping("/login")
	public ResponseEntity<String> loginCounselor(@Valid @RequestBody LoginDTO loginDTO) {
		return counselorService.loginCounselor(loginDTO);
	}

	/**
	 * Updates the name of a counselor.
	 * 
	 * @param id   the ID of the counselor to update
	 * @param name the new name for the counselor
	 * @return ResponseEntity with success message or error message if update fails
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateCounselor(@PathVariable Integer id, @RequestParam String name) {
		return counselorService.updateCounselor(id, name);
	}

	/**
	 * Updates phone number
	 * 
	 * @param id          the ID of the counselor to update
	 * @param phoneNumber the new phone number for the counselor
	 * @return ResponseEntity with success message or error message if update fails
	 */
	@PutMapping("/updatePhone/{id}")
	public ResponseEntity<String> updatePhoneNumber(@PathVariable Integer id, @RequestParam String phoneNumber) {
		return counselorService.updatePhoneNumber(id, phoneNumber);
	}

	/**
	 * Updates email
	 * 
	 * @param id    the ID of the counselor to update
	 * @param email the new email for the counselor
	 * @return ResponseEntity with success message or error message if update fails
	 */
	@PutMapping("/updateEmail/{id}")
	public ResponseEntity<String> updateEmail(@PathVariable Integer id, @RequestParam String email) {
		return counselorService.updateEmail(id, email);
	}

	/**
	 * Deletes a counselor by ID.
	 * 
	 * @param id the ID of the counselor to delete
	 * @return ResponseEntity with success message or error message if deletion
	 *         fails
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCounselor(@PathVariable Integer id) {
		return counselorService.deleteCounselor(id);
	}

	/**
	 * Gets the details of enquires by counselor ID with pagination.
	 * 
	 * @param id   the ID of the counselor
	 * @param page the page number for pagination
	 * @return
	 * @return ResponseEntity with counselor details or error message if not found
	 */
	@GetMapping("/enquiries/{id}")
	public ResponseEntity<List<Enquiry>> getEnquiriesByCid(@PathVariable Integer id) {
		return counselorService.getEnquiriesByCounselorId(id);
	}
}
