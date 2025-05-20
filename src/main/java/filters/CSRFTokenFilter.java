package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class CSRFTokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        // Bỏ qua tài nguyên tĩnh (nếu có)
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.contains("resources")) {
            chain.doFilter(request, response);
            return;
        }

        // Nếu là GET: tạo token mới và gán vào request
        if ("GET".equalsIgnoreCase(req.getMethod())) {
            String csrfToken = UUID.randomUUID().toString();
            req.getSession().setAttribute("csrfToken", csrfToken);
            req.setAttribute("csrfToken", csrfToken); // để form.jsp đọc được
        }

        // Nếu là POST: kiểm tra token
        if ("POST".equalsIgnoreCase(req.getMethod())) {
            String tokenSession = (String) req.getSession().getAttribute("csrfToken");
            String tokenRequest = req.getParameter("csrfToken");

            if (tokenSession == null || tokenRequest == null || !tokenSession.equals(tokenRequest)) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF token không hợp lệ hoặc đã hết hạn.");
                return;
            }

            // Token đúng → XÓA để không dùng lại được nữa (1 lần)
            req.getSession().removeAttribute("csrfToken");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}
