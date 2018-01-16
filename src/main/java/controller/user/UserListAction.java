package controller.user;

import controller.Action;
import controller.Forward;
import domain.user.User;
import org.apache.log4j.Logger;
import service.ServiceException;
import service.user.UserService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserListAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(UserListAction.class));
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService service = getServiceFactory().getUserService();
            List<User> users = service.findAll();
            req.setAttribute("users", users);
            return null;
        } catch (FactoryException | ServiceException e) {
            LOGGER.error("UserListAction problem with services " + e);
            throw new ServletException(e);
        }
    }
}
