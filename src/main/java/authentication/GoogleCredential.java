package authentication;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.client.util.store.FileDataStoreFactory;

import jakarta.servlet.ServletContext;

public class GoogleCredential {
    // Scope mới với quyền chỉnh sửa lịch
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    
    // Lưu trữ ServletContext để truy cập tài nguyên
    private ServletContext servletContext;

    public GoogleCredential(ServletContext servletContext) {
        this.servletContext = servletContext;
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
                JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline") // Cần có refresh token
                .build();
        
        // Set receiver để nhận mã xác thực từ trình duyệt
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        // Tạo AuthorizationCodeInstalledApp và yêu cầu cấp quyền lại
        AuthorizationCodeInstalledApp authorizationCodeInstalledApp = new AuthorizationCodeInstalledApp(flow, receiver);

		/*
		 * // Yêu cầu cấp quyền lại bằng cách thêm tham số 'prompt=consent' String
		 * authorizationUrl = flow.newAuthorizationUrl()
		 * .setRedirectUri(receiver.getRedirectUri()) .set("prompt", "consent") // Buộc
		 * người dùng cấp quyền lại .build();
		 * 
		 * // In URL xác thực để người dùng có thể mở trong trình duyệt và cấp quyền
		 * System.out.println("Mở URL sau để cấp quyền: " + authorizationUrl);
		 */

        // Sau khi người dùng cấp quyền, nhận mã xác thực
        return authorizationCodeInstalledApp.authorize("user");
    }
}
