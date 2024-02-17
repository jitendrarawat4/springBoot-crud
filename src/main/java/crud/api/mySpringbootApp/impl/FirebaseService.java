package crud.api.mySpringbootApp.impl;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import crud.api.mySpringbootApp.model.Student;

/**
 * The type Firebase Service.
 *
 * @author Jitendra Rawat
 */
@Service
public class FirebaseService {

	public String saveStudentDetails(Student student) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("students").document().set(student);
		
		return collectionsApiFuture.get().getUpdateTime().toString();
	}
}
