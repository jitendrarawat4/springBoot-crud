package crud.api.mySpringbootApp.firebase;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * The type initialize Firebase_Configuration.
 *
 * @author Jitendra Rawat
 */
@Service
public class FirebaseConfiguration {

	@PostConstruct
	public void initialize() {

		try {
			
			if (FirebaseApp.getApps().isEmpty()) {
			FileInputStream serviceAccount = new FileInputStream("./firebaseServiceAccountKey.json");

			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

			FirebaseApp.initializeApp(options);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
