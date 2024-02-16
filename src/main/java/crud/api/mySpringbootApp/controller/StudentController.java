package crud.api.mySpringbootApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud.api.mySpringbootApp.exception.ResourceNotFoundException;
import crud.api.mySpringbootApp.model.Student;
import crud.api.mySpringbootApp.repository.StudentRepository;

/**
 * The type Student controller.
 *
 * @author Jitendra Rawat
 */

@RestController
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	/**
	 * Get all students list.
	 *
	 * @return the list
	 */
	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	/**
	 * Get student by id.
	 *
	 * @param studentId the student id
	 * @return the students by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getUsersById(@PathVariable(value = "id") Long studentId) throws ResourceNotFoundException {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found on :: " + studentId));
		return ResponseEntity.ok().body(student);
	}

}
