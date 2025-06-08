package com.tyss.ca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.ca.dto.CouselorDTO;
import com.tyss.ca.dto.LoginDTO;
import com.tyss.ca.entity.Counselor;
import com.tyss.ca.entity.Enquiry;
import com.tyss.ca.exception.CounselorNotFoundException;
import com.tyss.ca.repository.CounselorRepo;

@Service
public class CounselorService {

	@Autowired
	private CounselorRepo counselorRepo;

	/**
	 * Registers a new counselor.
	 * 
	 * @param counselorDto the DTO containing counselor details
	 * @return ResponseEntity with success message or CounselorNotFoundException
	 *         will be thrown if the counselor already exists
	 */
	public ResponseEntity<String> registerCounselor(CouselorDTO counselorDto) {
		Optional<Counselor> opt = counselorRepo.findByEmail(counselorDto.getEmail());

		if (opt.isPresent()) {
			// TODO : throw new CounselorNotFoundException("Counselor with this email
			// already exists");
			throw new CounselorNotFoundException("Counselor with this email already exists");
		} else {
			// TODO : Register the counselor
			Counselor counselor = new Counselor();

			BeanUtils.copyProperties(counselorDto, counselor);

			counselorRepo.save(counselor);

			// TODO : Return success message
			return new ResponseEntity<String>("Counselor registered successfully", HttpStatus.CREATED);
		}

	}

	/**
	 * Logs in a counselor.
	 * 
	 * @param loginDTO the DTO containing login credentials
	 * @return ResponseEntity with success message or error message if login fails
	 */
	public ResponseEntity<String> loginCounselor(LoginDTO loginDTO) {
		Optional<Counselor> result = counselorRepo.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

		if (result.isPresent()) {
			// TODO : Return success message
			return new ResponseEntity<String>("Counselor logged in successfully", HttpStatus.OK);
		} else {
			// TODO : Error message
			return new ResponseEntity<String>("Invalid email or password", HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Updates the name of a counselor.
	 * 
	 * @param id   the ID of the counselor to update
	 * @param name the new name for the counselor
	 * @return ResponseEntity with success message or CounselorNotFoundException
	 *         will be thrown if the counselor does not exist
	 */
	public ResponseEntity<String> updateCounselor(Integer id, String name) {
		Optional<Counselor> opt = counselorRepo.findById(id);

		if (opt.isPresent()) {
			Counselor counselor = opt.get();
			counselor.setName(name);
			counselorRepo.save(counselor);
			return new ResponseEntity<String>("Counselor Name updated successfully", HttpStatus.OK);
		}

		throw new CounselorNotFoundException("Counselor with ID " + id + " not found");
	}

	/**
	 * Deletes a counselor.
	 * 
	 * @param id the ID of the counselor to delete
	 * @return ResponseEntity with success message or CounselorNotFoundException
	 */
	public ResponseEntity<String> deleteCounselor(Integer id) {
		Counselor counselor = counselorRepo.findById(id)
				.orElseThrow(() -> new CounselorNotFoundException("Counselor with ID " + id + " not found"));
		counselorRepo.delete(counselor);
		return new ResponseEntity<String>("Counselor deleted successfully", HttpStatus.OK);
	}

	/**
	 * Gets a list of all enquiries for a specific counselor.
	 * 
	 * @param id the ID of the counselor
	 * @return ResponseEntity with a list of enquiries or CounselorNotFoundException
	 */
	public ResponseEntity<List<Enquiry>> getEnquiriesByCounselorId(Integer id) {
		Counselor counselor = counselorRepo.findById(id)
				.orElseThrow(() -> new CounselorNotFoundException("Counselor with ID " + id + " not found"));
		List<Enquiry> enquiries = counselor.getEnquiries();

		return new ResponseEntity<>(enquiries, HttpStatus.OK);
	}

}
