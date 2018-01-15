package controller.password;

import controller.Action;
import controller.Forward;
import domain.user.User;
import service.ServiceException;
import service.user.UserService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PasswordResetAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService service = getServiceFactory().getUserService();
            User user = service.findById(Integer.parseInt(req.getParameter("id")));
            if(user != null) {
                service.changePassword(user.getId(), user.getPassword(), null);
            }
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        } catch(NumberFormatException e) {}
        return new Forward("/user/list.html");
    }
}