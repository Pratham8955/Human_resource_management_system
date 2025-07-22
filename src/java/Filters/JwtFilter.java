package Filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import secretkey.JwtUtil;

@WebFilter(filterName = "JwtFilter", urlPatterns = {"/faces/Secure/*"})
public class JwtFilter implements Filter {

    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    private static final Map<String, String> roleBasedAccessMap = new HashMap<>();

    static {
        roleBasedAccessMap.put("Admin", "/faces/Secure/Admin/");
        roleBasedAccessMap.put("Employee", "/faces/Secure/Employee/");
        roleBasedAccessMap.put("Manager", "/faces/Secure/Manager/");
    }

    private final SecretKey secretKey = JwtUtil.getSecretKey();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        if (filterConfig != null && debug) {
            log("JwtFilter: Initializing filter");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader("Authorization");
        if (token == null) {
            token = (String) httpRequest.getSession().getAttribute("jwtToken");
        }
//        System.out.println("outside token:::" + token);
        if (token != null) {
            try {
//                System.out.println("filter key:::" + secretKey);
//                System.out.println("token recieved::" + token);
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                System.out.println("token:::" + token);

                String role = claims.get("role", String.class);
                String userId = claims.get("userId", String.class);
                String requestURI = httpRequest.getRequestURI();

                log("JWT Token Validated. Role: " + role + ", Request URI: " + requestURI);

                if (isAuthorizedForRole(role, requestURI)) {
                    httpRequest.setAttribute("role", role);
                    httpRequest.setAttribute("userId", userId);
                    chain.doFilter(request, response);
                } else {
                    log("Access denied for role: " + role + ", Request URI: " + requestURI);
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied for the specified role.");
                }
            } catch (Exception e) {
                log("Invalid token: " + e.getMessage());
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token.");
            }
        } else {
            log("Authorization token is missing.");
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization token is missing.");
        }
    }

    private boolean isAuthorizedForRole(String role, String requestURI) {
        // Get the context path
        String contextPath = filterConfig.getServletContext().getContextPath();

        // Remove the context path from the request URI
        String relativeURI = requestURI.substring(contextPath.length());

        // Retrieve the allowed path for the role
        String allowedPath = roleBasedAccessMap.get(role);

        // Debugging logs
        log("Context Path: " + contextPath);
        log("Relative URI: " + relativeURI);
        log("Allowed Path for Role " + role + ": " + allowedPath);

        // Check if the relative URI starts with the allowed path
        return allowedPath != null && relativeURI.startsWith(allowedPath);
    }

    @Override
    public void destroy() {
        // Clean-up code if needed
    }

    private void log(String msg) {
        if (filterConfig != null) {
            filterConfig.getServletContext().log(msg);
        }
    }
}
