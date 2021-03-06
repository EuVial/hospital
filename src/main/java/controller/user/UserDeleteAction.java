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

public class UserDeleteAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(UserDeleteAction.class));

    @Override
    public Forward execute(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException ignored) { }
        if (id != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = service.findById(id);
                service.delete(user);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("UserDeleteAction problem with services " + e);
                throw new ServletException(e);
            }
        }
        return new Forward("/user/list.html");
    }
}
