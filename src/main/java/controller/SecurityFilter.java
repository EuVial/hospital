package controller;

import domain.user.User;
import domain.user.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {
    private static final Map<String, Set<UserRole>> permissions = new HashMap<>();

    static {
        Set<UserRole> all = new HashSet<>();
        all.addAll(Arrays.asList(UserRole.values()));
        Set<UserRole> admin = new HashSet<>();
        admin.add(UserRole.ADMIN);
        Set<UserRole> doctor = new HashSet<>();
        doctor.add(UserRole.DOCTOR);
        Set<UserRole> employees = new HashSet<>();
        employees.add(UserRole.DOCTOR);
        employees.add(UserRole.NURSE);

//        permissions.put("/", null);
//        permissions.put("/index", null);
//        permissions.put("/login", null);

        permissions.put("/logout", all);
        permissions.put("/password/edit", all);
        permissions.put("/password/save", all);
        permissions.put("/password/reset", admin);

        permissions.put("/user/list", admin);
        permissions.put("/user/edit", admin);
        permissions.put("/user/save", admin);
        permissions.put("/user/delete", admin);

        permissions.put("/patient/list", employees);
        permissions.put("/patient/view", employees);
        permissions.put("/patient/edit", doctor);
        permissions.put("/patient/save", doctor);
        permissions.put("/patient/delete", doctor);
        permissions.put("/patient/discharge", doctor);
        permissions.put("/patient/discharge/done", doctor);

        permissions.put("/patient/view/diagnosis/view", employees);
        permissions.put("/patient/view/diagnosis/edit", doctor);
        permissions.put("/patient/view/diagnosis/save", doctor);
        permissions.put("/patient/view/diagnosis/delete", doctor);

        permissions.put("/patient/view/disease_history", employees);

        permissions.put("/patient/view/treatment/list", employees);
        permissions.put("/patient/view/treatment/done", employees);
        permissions.put("/patient/view/treatment/edit", doctor);
        permissions.put("/patient/view/treatment/save", doctor);
        permissions.put("/patient/view/treatment/delete", doctor);
        permissions.put("/patient/view/treatment/view", employees);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)req;
        HttpServletResponse httpResp = (HttpServletResponse)resp;
        String url = httpReq.getRequestURI();
        String context = httpReq.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if (postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        Set<UserRole> roles = permissions.get(url);
        if (roles != null) {
            HttpSession session = httpReq.getSession(false);
            if (session != null) {
                User user = (User)session.getAttribute("currentUser");
                if (user != null && roles.contains(user.getRole())) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        } else {
            chain.doFilter(req, resp);
            return;
        }
//        httpResp.sendRedirect(context + "/login.html");
        httpResp.sendRedirect(context + "/login.html?message=login.message.access.denied");
    }

    @Override
    public void destroy() {}
}
