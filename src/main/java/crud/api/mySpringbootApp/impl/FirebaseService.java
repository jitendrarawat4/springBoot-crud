package crud.api.mySpringbootApp.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.DocumentSnapshot;

import crud.api.mySpringbootApp.exception.ResourceNotFoundException;
import crud.api.mySpringbootApp.model.Student;

/**
 * The type Firebase Service.
 *
 * @author Jitendra Rawat
 */
@Service
public class FirebaseService {

	public String saveStudentDetails(Student student, Long studentId) throws InterruptedException, ExecutionException {
		student.setId(studentId);

		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection("students").whereEqualTo("id", studentId).get();

		QuerySnapshot documents = querySnapshot.get();

		if (documents.isEmpty()) {

			dbFirestore.collection("students").document().set(student);

			return "Student with ID: " + studentId + " Created Successfully!";
		} else
			return "Student with ID: " + studentId + " Already Exist!";
	}

	public List<Student> findAll() throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Firestore dbFirestore = FirestoreClient.getFirestore();

		ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection("students").get();

		QuerySnapshot documents = querySnapshot.get();

		List<Student> studentList = new ArrayList<>();

		for (QueryDocumentSnapshot document : documents) {

			Student student = document.toObject(Student.class);
			studentList.add(student);
		}

		if (!studentList.isEmpty()) {

			return studentList;
		} else
			throw new ResourceNotFoundException("No Records available");
	}

	public Student findById(Long studentId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection("students").whereEqualTo("id", studentId).get();

		QuerySnapshot documents = querySnapshot.get();

		if (!documents.isEmpty()) {
			DocumentSnapshot document = documents.getDocuments().get(0);

			Student student = document.toObject(Student.class);
			return student;
		} else {
			throw new ResourceNotFoundException("Student not found with ID: " + studentId);
		}
	}

	public String updateStudentDetails(Student student, Long studentId)
			throws InterruptedException, ExecutionException {

		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection("students").whereEqualTo("id", studentId).get();

		QuerySnapshot documents = querySnapshot.get();

		if (!documents.isEmpty()) {

			dbFirestore.collection("students").document().set(student);

			return "Student with ID: " + studentId + " Updated Successfully!";
		} else
			return "Student with ID: " + studentId + " Not Exist!";
	}

	public String deleteStudent(Long id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> collectionsApiFuture = dbFirestore.collection("students").whereEqualTo("id", id).get();

		QuerySnapshot documents = collectionsApiFuture.get();

		if (!documents.isEmpty()) {
			for (QueryDocumentSnapshot document : documents) {
				dbFirestore.collection("students").document(document.getId()).delete();
			}
			return "Student with ID: " + id + " deleted Successfully!";
		} else {
			return "Student with ID: " + id + " not found!";
		}
	}
}
