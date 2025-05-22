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
import java.util.UUID;

/**
 * Servlet Filter implementation class ContentSecurityPolicyFilter
 */
public class ContentSecurityPolicyFilter implements Filter {
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        HttpServletResponse httpResponse = (HttpServletResponse) response;

	        // Tạo nonce
	        String nonce = UUID.randomUUID().toString();
	        request.setAttribute("cspNonce", nonce);

	        // Gán CSP Header sử dụng nonce
	        httpResponse.setHeader("Content-Security-Policy",
	        	    "default-src 'self'; " +
	        	    "script-src 'self' https://code.jquery.com https://cdn.jsdelivr.net https://cdnjs.cloudflare.com 'nonce-" + nonce + "'; " +
	        	    "style-src 'self' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com 'nonce-" + nonce + "'; " +
	        	    "font-src 'self' https://cdn.jsdelivr.net data:; " + 
	        	    "img-src 'self' data: https://cdn-icons-png.flaticon.com; " +
	        	    "media-src 'self' https://cdn-icons-mp4.flaticon.com; " +
	        	    "connect-src 'self'; " +
	        	    "object-src 'none'; " +
	        	    "frame-ancestors 'none';"
	        	);



	        chain.doFilter(request, response);
	    }
}
