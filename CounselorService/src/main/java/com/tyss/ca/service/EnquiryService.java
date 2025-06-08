package com.tyss.ca.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.ca.dto.FilterDTO;
import com.tyss.ca.entity.Counselor;
import com.tyss.ca.entity.Enquiry;
import com.tyss.ca.enums.ClassMode;
import com.tyss.ca.exception.CounselorNotFoundException;
import com.tyss.ca.exception.EnquiryNotFoundException;
import com.tyss.ca.repository.CounselorRepo;
import com.tyss.ca.repository.EnquiryRepo;

@Service
public class EnquiryService {

	@Autowired
	private EnquiryRepo enquiryRepo;

	@Autowired
	private CounselorRepo counselorRepo;

	/**
	 * Adds a new enquiry for a specific counselor.
	 * 
	 * @param cid     the ID of the counselor
	 * @param enquiry the enquiry details to be added
	 * @return ResponseEntity with the created enquiry or an error message if the
	 *         counselor is not found
	 */
	public ResponseEntity<Enquiry> addEnquiry(Integer cid, Enquiry enquiry) {
		Counselor counselor = counselorRepo.findById(cid)
				.orElseThrow(() -> new CounselorNotFoundException("Counselor not found with id: " + cid));

		enquiry.setCounselor(counselor);

		Enquiry savedEnquiry = enquiryRepo.save(enquiry);

		return new ResponseEntity<Enquiry>(savedEnquiry, HttpStatus.CREATED);
	}

	/**
	 * Updates the class mode of an enquiry by its ID.
	 * 
	 * @param id        the ID of the enquiry to be updated
	 * @param classMode the new class mode to be set
	 * @return ResponseEntity with the updated enquiry or an error message if the
	 *         enquiry is not found
	 */
	public ResponseEntity<Enquiry> updateEnquiry(Integer id, ClassMode classMode) {
		Enquiry enquiry = enquiryRepo.findById(id)
				.orElseThrow(() -> new EnquiryNotFoundException("Enquiry not found with id: " + id));

		enquiry.setClassMode(classMode);

		Enquiry updatedEnquiry = enquiryRepo.save(enquiry);

		return new ResponseEntity<Enquiry>(updatedEnquiry, HttpStatus.OK);
	}

	/**
	 * Filters enquiries based on the provided filter criteria.
	 * 
	 * @param filterDTO the DTO containing filter criteria such as ClassMode{
	 *                  ONLINE, OFFLINE, HYBRID }, Course{ JAVA_FULL_STACK,
	 *                  PYTHON_FULL_STACK, MEAN_STACK, MERN_STACK, DATA_SCIENCE,
	 *                  AI_ML, WEB_DEVELOPMENT, ANDROID_DEVELOPMENT, IOT, DEVOPS,
	 *                  CLOUD_COMPUTING }, and Status{ ACTIVE, INACTIVE }
	 * @return ResponseEntity with a list of enquiries that match the filter
	 *         criteria
	 */
	public ResponseEntity<Page<Enquiry>> filterEnquiry(Integer pageNumber, FilterDTO filterDTO) {

		Enquiry enquiry = new Enquiry();

		BeanUtils.copyProperties(filterDTO, enquiry);

		Example<Enquiry> example = Example.of(enquiry);

		Pageable pageable = PageRequest.of(pageNumber - 1, 5);

		Page<Enquiry> all = enquiryRepo.findAll(example, pageable);

		return new ResponseEntity<Page<Enquiry>>(all, HttpStatus.OK);
	}

	/**
	 * Deletes an enquiry by its ID.
	 * 
	 * @param id the ID of the enquiry to be deleted
	 * @return ResponseEntity with success message or error message if the enquiry
	 *         is not found
	 */
	public ResponseEntity<String> deleteEnquiry(Integer id) {
		Enquiry enquiry = enquiryRepo.findById(id)
				.orElseThrow(() -> new EnquiryNotFoundException("Enquiry not found with id: " + id));
		enquiryRepo.delete(enquiry);
		return new ResponseEntity<String>("Enquiry deleted successfully", HttpStatus.OK);
	}

	/**
	 * Gets the details of enquiries by counselor ID.
	 * 
	 * @param cid        the ID of the counselor
	 * @param pageNumber the page number for pagination
	 * @return ResponseEntity with a list of enquiries associated with the counselor
	 */
	public ResponseEntity<?> getEnquiriesByCid(Integer cid, Integer pageNumber) {

		Pageable pageable = PageRequest.of(pageNumber - 1, 5);

		Page<Enquiry> all = enquiryRepo.findByCounselorId(cid, pageable);

		return new ResponseEntity<Page<Enquiry>>(all, HttpStatus.OK);
	}

}
