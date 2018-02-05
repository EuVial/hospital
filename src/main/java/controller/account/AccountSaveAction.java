package controller.account;

import constants.Constants;
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

public class AccountSaveAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(AccountSaveAction.class));

    @Override
    public Forward execute(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute("currentUser");
        user.setFirstName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));

        if (user.getFirstName() != null
                && user.getLastName() != null
                && user.getFirstName().matches(Constants.REGEX_NAME)
                && user.getLastName().matches(Constants.REGEX_NAME)) {
            try {
                UserService service = getServiceFactory().getUserService();
                service.save(user);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("AccountSaveAction problem with services " + e);
                throw new ServletException(e);
            }
        } else {
            return new Forward("/account/edit.html?message=message.incorrect.data");
        }
        return new Forward("/index.html");
    }
}
