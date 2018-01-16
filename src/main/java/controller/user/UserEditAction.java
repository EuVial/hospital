package controller.user;

import controller.Action;
import controller.Forward;
import domain.user.User;
import domain.user.UserRole;
import org.apache.log4j.Logger;
import service.ServiceException;
import service.user.UserService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserEditAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(UserEditAction.class));
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {}
        if (id != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = service.findById(id);
                req.setAttribute("user", user);
                boolean userCanBeDeleted = service.canDelete(user);
                req.setAttribute("userCanBeDeleted", userCanBeDeleted);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("UserEditAction problem with services " + e);
                throw new ServletException(e);
            }
        }
        req.setAttribute("roles", UserRole.values());
        return null;
    }
}
