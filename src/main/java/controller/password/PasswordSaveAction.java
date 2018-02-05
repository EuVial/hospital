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

public class PasswordSaveAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(PasswordSaveAction.class));

    @Override
    public Forward execute(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        String oldPassword = req.getParameter("old-password");
        String newPassword = req.getParameter("new-password");
        String newPasswordRepeat = req.getParameter("new-password-repeat");
        if (oldPassword != null && newPassword != null && newPasswordRepeat != null
                && newPassword.equals(newPasswordRepeat)) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = (User) req.getSession(false).getAttribute("currentUser");
                if (!oldPassword.equals(user.getPassword()))
                    return new Forward("/password/edit.html?message=password.message.not.equals");
                service.changePassword(user.getId(), oldPassword, newPassword);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("PasswordSaveAction problem with services" + e);
                throw new ServletException(e);
            }
        } else {
            return new Forward("/password/edit.html?message=password.message.incorrect.data");
        }
        return new Forward("/index.html");

    }
}
