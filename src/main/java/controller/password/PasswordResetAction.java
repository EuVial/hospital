package controller.password;

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

public class PasswordResetAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(PasswordResetAction.class));
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService service = getServiceFactory().getUserService();
            User user = service.findById(Integer.parseInt(req.getParameter("id")));
            if (user != null) {
                service.changePassword(user.getId(), user.getPassword(), null);
            }
        } catch (FactoryException | ServiceException e) {
            LOGGER.error("Can't change password record to null" + e);
            throw new ServletException(e);
        } catch (NumberFormatException ignored) {}
        return new Forward("/user/list.html");
    }
}