package controller;

import domain.user.User;
import service.ServiceException;
import service.user.UserService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = service.findByLoginAndPassword(login, password);
                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("currentUser", user);
                    return new Forward("/index.html");
                } else {
//                    return new Forward("/login.html?message=login.message");
                    return new Forward("/login.html?message=login.message.incorrect.password");
                }
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
