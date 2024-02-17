package crud.api.mySpringbootApp.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/addStudent")
	public String addStudent(@RequestBody Student student) throws InterruptedException, ExecutionException {
		return firebaseService.saveStudentDetails(student);
	}

//	/**
//	 * Get all students list.
//	 *
//	 * @return the list
//	 */
//	@GetMapping("/students")
//	public List<Student> getAllStudents() {
//		return firebaseService.findAll();
//	}

//	/**
//	 * Get student by id.
//	 *
//	 * @param studentId the student id
//	 * @return the students by id
//	 * @throws ResourceNotFoundException the resource not found exception
//	 */
//	@GetMapping("/students/{id}")
//	public ResponseEntity<Student> getUsersById(@PathVariable(value = "id") Long studentId) throws ResourceNotFoundException {
//		Student student = studentRepository.findById(studentId)
//				.orElseThrow(() -> new ResourceNotFoundException("Student not found on :: " + studentId));
//		return ResponseEntity.ok().body(student);
//	}

}
