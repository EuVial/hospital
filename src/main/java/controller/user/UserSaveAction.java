package controller.user;

import constants.Constants;
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

public class UserSaveAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(UserSaveAction.class));

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        Integer userId = null;
        try {
            userId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException ignored) {}
        if (userId != null) user.setId(userId);
        user.setLogin(req.getParameter("login"));
        user.setFirstName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));
        try {
            user.setRole(UserRole.values()[Integer.parseInt(req.getParameter("role"))]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {}
        if (    user.getLogin() != null &&
                user.getRole() != null &&
                user.getFirstName() != null &&
                user.getLastName() != null &&
                user.getLogin().matches(Constants.REGEX_LOGIN) &&
                user.getFirstName().matches(Constants.REGEX_NAME) &&
                user.getLastName().matches(Constants.REGEX_NAME)) {
            try {
                UserService service = getServiceFactory().getUserService();
                service.save(user);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("UserSaveAction problem with services " + e);
                throw new ServletException(e);
            }
        } else {
            if (userId != null) {
                return new Forward("/user/edit.html?id=" + userId + "&message=message.incorrect.data");
            } else {
                return new Forward("/user/edit.html?message=message.incorrect.data");
            }
        }
        return new Forward("/user/list.html");
    }
}
