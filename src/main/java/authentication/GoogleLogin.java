package authentication;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
public class GoogleLogin {
	 private static final String CLIENT_ID = "503615320731-kcpnsnsjmng7vusmcm110s6m35c7d0iv.apps.googleusercontent.com";
	    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	    private static final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	    public static GoogleIdToken.Payload verifyToken(String idTokenString) throws Exception {
	        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
	                .setAudience(Collections.singletonList(CLIENT_ID)) // Set your client ID
	                .build();

	        GoogleIdToken idToken = verifier.verify(idTokenString);
	        if (idToken != null) {
	            return idToken.getPayload();
	        } else {
	            throw new Exception("Invalid ID token.");
	        }
	    }
}
