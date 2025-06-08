package com.tyss.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.tyss.ca.dto.FilterDTO;
import com.tyss.ca.entity.Enquiry;
import com.tyss.ca.enums.ClassMode;
import com.tyss.ca.enums.Course;
import com.tyss.ca.enums.Status;
import com.tyss.ca.service.EnquiryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/enquiry")
public class EnquiryController {

	@Autowired
	private EnquiryService enquiryService;

	/**
	 * Adds a new enquiry for a specific counselor.
	 * 
	 * @param cid     the ID of the counselor
	 * @param enquiry the enquiry details to be added
	 * @return ResponseEntity with the created enquiry or an error message if the
	 *         counselor is not found
	 */
	@PostMapping("/add/{cid}")
	public ResponseEntity<Enquiry> addEnquiry(@PathVariable Integer cid, @Valid @RequestBody Enquiry enquiry) {
		return enquiryService.addEnquiry(cid, enquiry);
	}

	/**
	 * Updates the class mode of an enquiry by its ID.
	 * 
	 * @param id                the ID of the enquiry to be updated
	 * @param classMode{ONLINE, OFFLINE, HYBRID} the new class mode to be set
	 * @return ResponseEntity with the updated enquiry or an error message if the
	 *         enquiry is not found
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<Enquiry> updateEnquiry(@PathVariable Integer id, @RequestParam ClassMode classMode) {
		return enquiryService.updateEnquiry(id, classMode);
	}

	/**
	 * Updates the status of an enquiry by its ID.
	 * 
	 * @param id             the ID of the enquiry to be updated
	 * @param status{ACTIVE, INACTIVE} the new status to be set
	 * @return ResponseEntity with the updated enquiry or an error message if the
	 *         enquiry is not found
	 */
	@PutMapping("/update/{id}/status")
	public ResponseEntity<Enquiry> updateEnquiryStatus(@PathVariable Integer id, @RequestParam Status status) {
		return enquiryService.updateEnquiryStatus(id, status);
	}

	/**
	 * Updates the course of an enquiry by its ID.
	 * 
	 * @param id                      the ID of the enquiry to be updated
	 * @param course{JAVA_FULL_STACK, PYTHON_FULL_STACK, MEAN_STACK, MERN_STACK,
	 *                                DATA_SCIENCE, AI_ML, WEB_DEVELOPMENT,
	 *                                ANDROID_DEVELOPMENT, IOT, DEVOPS,
	 *                                CLOUD_COMPUTING} the new course to be set
	 * @return ResponseEntity with the updated enquiry or an error message if the
	 *         enquiry is not found
	 */
	@PutMapping("/update/{id}/course")
	public ResponseEntity<Enquiry> updateEnquiryCourse(@PathVariable Integer id, @RequestParam Course course) {
		return enquiryService.updateEnquiryCourse(id, course);
	}

	/**
	 * Updates the phone of an enquiry by its ID.
	 * 
	 * @param id    the ID of the enquiry to be updated
	 * @param phone the new phone to be set
	 * @return ResponseEntity with the updated enquiry or an error message if the
	 *         enquiry is not found
	 */
	@PutMapping("/update/{id}/phone")
	public ResponseEntity<Enquiry> updateEnquiryPhone(@PathVariable Integer id, @RequestParam Long phoneNumber) {
		return enquiryService.updateEnquiryPhone(id, phoneNumber);
	}

	/**
	 * Filters enquiries based on the provided filter criteria.
	 * 
	 * @param filterDTO the DTO containing filter criteria such as ClassMode{ONLINE,
	 *                  OFFLINE, HYBRID}, Course{JAVA_FULL_STACK, PYTHON_FULL_STACK,
	 *                  MEAN_STACK, MERN_STACK, DATA_SCIENCE, AI_ML,
	 *                  WEB_DEVELOPMENT, ANDROID_DEVELOPMENT, IOT, DEVOPS,
	 *                  CLOUD_COMPUTING}, and Status{ACTIVE, INACTIVE}
	 * @return ResponseEntity with a list of enquiries that match the filter
	 *         criteria
	 */
	@GetMapping("/filter/{pageNumber}")
	public ResponseEntity<Page<Enquiry>> filterEnquiry(@PathVariable Integer pageNumber,
			@RequestBody FilterDTO filterDTO) {
		return enquiryService.filterEnquiry(pageNumber, filterDTO);
	}

	/**
	 * Deletes an enquiry by ID.
	 * 
	 * @param id the ID of the enquiry to delete
	 * @return ResponseEntity with success message or error message if deletion
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEnquiry(@PathVariable Integer id) {
		return enquiryService.deleteEnquiry(id);
	}

	/**
	 * Get all inquiries by its Counselor.
	 * 
	 * @param id         the ID of the counselor
	 * @param pageNumber the page number for pagination
	 * @return ResponseEntity with the enquiry details or error message if not found
	 */
	@GetMapping("/enqs/cid/{cid}/page/{pageNumber}")
	public ResponseEntity<?> getEnquiriesByCid(@PathVariable Integer cid, @PathVariable Integer pageNumber) {
		return enquiryService.getEnquiriesByCid(cid, pageNumber);
	}

}
