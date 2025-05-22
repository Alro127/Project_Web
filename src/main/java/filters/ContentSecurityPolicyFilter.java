package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet Filter implementation class ContentSecurityPolicyFilter
 */
public class ContentSecurityPolicyFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// CSP - Ngăn XSS và chỉ cho phép script từ các nguồn đáng tin cậy
        httpResponse.setHeader("Content-Security-Policy",
            "default-src 'self'; " +
            "script-src 'self' 'unsafe-inline' https://accounts.google.com https://code.jquery.com https://cdn.jsdelivr.net https://cdnjs.cloudflare.com https://stackpath.bootstrapcdn.com; " +
            "style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com https://stackpath.bootstrapcdn.com; " +
            "font-src 'self' https://cdn.jsdelivr.net; " +
            "img-src 'self' data: https://cdn-icons-png.flaticon.com; " +
            "media-src 'self' https://cdn-icons-mp4.flaticon.com; " +
            "frame-ancestors 'none'; " +                 // Chặn clickjacking
            "object-src 'none';"                         // Không cho phép Flash/Java applets
        );

        // X-Content-Type-Options - Ngăn trình duyệt đoán kiểu MIME
        httpResponse.setHeader("X-Content-Type-Options", "nosniff");

        // X-Frame-Options - Ngăn trang bị nhúng trong iframe (Clickjacking)
        httpResponse.setHeader("X-Frame-Options", "DENY");

        // X-XSS-Protection - Tắt XSS Auditor cũ, bật chế độ block nếu còn hoạt động
        httpResponse.setHeader("X-XSS-Protection", "1; mode=block");

        // Referrer-Policy - Giới hạn thông tin referrer gửi đi
        httpResponse.setHeader("Referrer-Policy", "no-referrer");

        // Permissions-Policy - Ngăn camera, mic, v.v. nếu không cần
        httpResponse.setHeader("Permissions-Policy", "camera=(), microphone=(), geolocation=()");

        chain.doFilter(request, response);

	}
}
