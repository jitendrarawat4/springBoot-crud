package crud.api.mySpringbootApp.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import crud.api.mySpringbootApp.exception.ResourceNotFoundException;
import crud.api.mySpringbootApp.impl.FirebaseService;
import crud.api.mySpringbootApp.model.Student;

/**
 * The type Student controller.
 *
 * @author Jitendra Rawat
 */

@RestController
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
	private FirebaseService firebaseService;

	@PostMapping("/student")
	public String addStudent(@RequestBody Student student, @RequestParam(value = "id") Long studentId) throws InterruptedException, ExecutionException {
		return firebaseService.saveStudentDetails(student, studentId);
	}

	@GetMapping("/students")
	public List<Student> getAllStudents() throws InterruptedException, ExecutionException, ResourceNotFoundException {
		return firebaseService.findAll();
	}

	@GetMapping("/student")
	public ResponseEntity<Student> getStudentById(@RequestParam(value = "id") Long studentId)
			throws ResourceNotFoundException, InterruptedException, ExecutionException {
		Student student = firebaseService.findById(studentId);
				
		return ResponseEntity.ok().body(student);
	}
	
	@PutMapping("/student")
	public String updateStudent(@RequestBody Student student,@RequestParam(value = "id") Long studentId) throws InterruptedException, ExecutionException {
		return firebaseService.updateStudentDetails(student, studentId);
	}
	
	@DeleteMapping("/student")
	public String deleteStudentById(@RequestParam(value = "id") Long studentId)
			throws ResourceNotFoundException, InterruptedException, ExecutionException {
				
		return firebaseService.deleteStudent(studentId);
	}

}
