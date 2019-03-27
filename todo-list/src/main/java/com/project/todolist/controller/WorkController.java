package com.project.todolist.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.todolist.exception.ResourceNotFoundException;
import com.project.todolist.model.Work;
import com.project.todolist.repository.WorkRepository;
import com.project.todolist.service.WorkService;

@RestController
@RequestMapping("/api")
public class WorkController {
	
	@Autowired
	private WorkService workService;

	@Autowired
    WorkRepository workRepository;
	
	// Add a new Work
	@PostMapping("/works")
	public List<Work> createWork(@Valid @RequestBody List<Work> work) {
	    return workRepository.saveAll(work);
	}
	
	// Edit a Work
	@PutMapping("/works/{id}")
	public Work updateNote(@PathVariable(value = "id") Long workId, @Valid @RequestBody Work workDetails) {

		// Throw exception whenever a work with given id not found
		Work work = workRepository.findById(workId).orElseThrow(()-> new ResourceNotFoundException("Work", "id", workId));

		work.setWorkName(workDetails.getWorkName());
		work.setStartingDate(workDetails.getStartingDate());
		work.setEndingDate(workDetails.getEndingDate());
		work.setStatus(workDetails.getStatus());

		Work updatedWork = workRepository.save(work);
	    return updatedWork;
	}
	
	// Delete a Note
	@DeleteMapping("/works/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
		
		// Throw exception whenever a work with given id not found
		Work work = workRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

		workRepository.delete(work);

	    return ResponseEntity.ok().build();
	}
	
	// Get All Works
	// Use Repository
//	@GetMapping("/works")
//	public ResponseEntity<List<Work>> getAllWorks() {
//		List<Work> listWorks = workRepository.findAll();
//		if(listWorks.isEmpty()) {
//			return new ResponseEntity<List<Work>>(HttpStatus.NO_CONTENT);
//		}
//	    return new ResponseEntity<List<Work>>(workRepository.findAll(), HttpStatus.OK);
//	}
	
	// Get All Works
	// Use native SQL
	@GetMapping("/works")
	public Map<String, Object> fetchAll() {
		return workService.fetchAll();
	}
	
}
