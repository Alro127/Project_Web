package authentication;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.auth.Credentials;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import jakarta.servlet.ServletContext;

public class GoogleCredential {
    // Scope mới với quyền chỉnh sửa lịch
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    // Lưu trữ ServletContext để truy cập tài nguyên
    private ServletContext servletContext;

    public GoogleCredential(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    
    public GoogleCredential() {
		
	}
    public Credential getCredentials() throws IOException, GeneralSecurityException {
        // Đọc file credentials.json từ thư mục WEB-INF
        InputStream in = servletContext.getResourceAsStream("/WEB-INF/classes/credential/credentials.json");
        if (in == null) {
            throw new IOException("Không tìm thấy file credentials.json");
        }

        // Tải thông tin client secrets
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        // Tạo flow OAuth với scope chỉnh sửa lịch
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY, clientSecrets,
                Arrays.asList( "https://www.googleapis.com/auth/userinfo.profile",
                		"https://www.googleapis.com/auth/userinfo.email",
                		"https://www.googleapis.com/auth/calendar"))
                //.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline") // Cần có refresh token
                .setApprovalPrompt("force") // Buộc hiển thị màn hình cấp quyền
                .build();
        
        // Set receiver để nhận mã xác thực từ trình duyệt
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        // Tạo AuthorizationCodeInstalledApp và yêu cầu cấp quyền lại
        AuthorizationCodeInstalledApp authorizationCodeInstalledApp = new AuthorizationCodeInstalledApp(flow, receiver);

        // Sau khi người dùng cấp quyền, nhận mã xác thực
        Credential authorizationCode = authorizationCodeInstalledApp.authorize("user");
        return authorizationCode;
    }
    
    public Calendar getCredentialCalendarService(String email) throws IOException, GeneralSecurityException {
        // Đọc file JSON Service Account
        InputStream serviceAccountStream = servletContext.getResourceAsStream("/WEB-INF/classes/credential/service-account-key.json");
        if (serviceAccountStream == null) {
            throw new IOException("Không tìm thấy tệp service-account-key.json");
        }
        
        // Tạo GoogleCredentials từ Service Account
        Set<String> scopes = new HashSet<>();
        scopes.add(CalendarScopes.CALENDAR); // Thêm scope lịch
        scopes.add("https://www.googleapis.com/auth/userinfo.email"); // Thêm scope email
        scopes.add("https://www.googleapis.com/auth/userinfo.profile"); // Thêm scope profile
        // Tạo GoogleCredentials từ Service Account
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream)
                .createScoped(scopes);

        // Gán quyền truy cập thay mặt người dùng (impersonate)
        credentials = credentials.createDelegated(email);
        
        // Tạo Calendar API Service
        return new Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("CV Hub")
                .build();
    }
    
    public TokenResponse refreshAccessToken(String refreshToken) throws IOException {
    	InputStream in = servletContext.getResourceAsStream("/WEB-INF/classes/credential/credentials.json");
    	GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
    	String clientId = clientSecrets.getDetails().getClientId();
        String clientSecret = clientSecrets.getDetails().getClientSecret();
    	TokenResponse response = new GoogleRefreshTokenRequest(
                new NetHttpTransport(),
                new JacksonFactory(),
                refreshToken, 
                clientId,
                clientSecret)
                .execute();
        System.out.println("Access token: " + response.getAccessToken());

        return response;
    }
}
