package servlets;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebFilter("/*")
public class ContentSecurityPolicyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Optional: Khởi tạo nếu cần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String nonce = UUID.randomUUID().toString().replace("-", "");
        request.setAttribute("cspNonce", nonce);

        String cspHeader = "default-src 'self'; " +
        	    "script-src 'self' 'nonce-" + nonce + "' https://code.jquery.com https://cdn.jsdelivr.net https://cdnjs.cloudflare.com https://stackpath.bootstrapcdn.com; " +
        	    "style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com; " +
        	    "font-src 'self' https://cdn.jsdelivr.net; " +
        	    "img-src 'self' data: https://cdn.jsdelivr.net; " +
        	    "media-src 'self' data:; " +
        	    "form-action 'self'; " +
        	    "frame-ancestors 'none';";

        httpResponse.setHeader("Content-Security-Policy", cspHeader);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Optional: Dọn dẹp nếu cần
    }
}
