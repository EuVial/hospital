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
    private static final Map<String, Set<UserRole>> PERMISSIONS = new HashMap<>();

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

        PERMISSIONS.put("/logout", all);

        PERMISSIONS.put("/password/edit", all);
        PERMISSIONS.put("/password/save", all);
        PERMISSIONS.put("/password/reset", admin);

        PERMISSIONS.put("/account/edit", all);
        PERMISSIONS.put("/account/save", all);

        PERMISSIONS.put("/user/list", admin);
        PERMISSIONS.put("/user/edit", admin);
        PERMISSIONS.put("/user/save", admin);
        PERMISSIONS.put("/user/delete", admin);

        PERMISSIONS.put("/patient/list", employees);
        PERMISSIONS.put("/patient/view", employees);
        PERMISSIONS.put("/patient/edit", doctor);
        PERMISSIONS.put("/patient/save", doctor);
        PERMISSIONS.put("/patient/delete", doctor);
        PERMISSIONS.put("/patient/discharge", doctor);
        PERMISSIONS.put("/patient/discharge/done", doctor);

        PERMISSIONS.put("/patient/view/diagnosis/view", employees);
        PERMISSIONS.put("/patient/view/diagnosis/edit", doctor);
        PERMISSIONS.put("/patient/view/diagnosis/save", doctor);
        PERMISSIONS.put("/patient/view/diagnosis/delete", doctor);

        PERMISSIONS.put("/patient/view/disease_history", employees);

        PERMISSIONS.put("/patient/view/treatment/list", employees);
        PERMISSIONS.put("/patient/view/treatment/done", employees);
        PERMISSIONS.put("/patient/view/treatment/edit", doctor);
        PERMISSIONS.put("/patient/view/treatment/save", doctor);
        PERMISSIONS.put("/patient/view/treatment/delete", doctor);
        PERMISSIONS.put("/patient/view/treatment/view", employees);
    }

    @Override
    public void init(final FilterConfig config) throws ServletException { }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        String url = httpReq.getRequestURI();
        String context = httpReq.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if (postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        Set<UserRole> roles = PERMISSIONS.get(url);
        if (roles != null) {
            HttpSession session = httpReq.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("currentUser");
                if (user != null && roles.contains(user.getRole())) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        } else {
            chain.doFilter(req, resp);
            return;
        }
        httpResp.sendRedirect(context + "/login.html?message=login.message.access.denied");
    }

    @Override
    public void destroy() { }
}
