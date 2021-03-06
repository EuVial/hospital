package controller;

import domain.user.User;
import org.apache.log4j.Logger;
import service.ServiceException;
import service.user.UserService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(LoginAction.class));

    @Override
    public Forward execute(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = service.findByLoginAndPassword(login, password);
                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("currentUser", user);
                    LOGGER.info("User successfully logged as " + user.getRole().getName());
                    return new Forward("/index.html");
                } else {
                    return new Forward("/login.html?message=login.message.incorrect.password");
                }
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("LoginAction problem with services " + e);
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
