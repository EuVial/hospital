package controller.patient.view.treatment;

import controller.Action;
import controller.Forward;
import domain.patient.Treatment;
import domain.user.User;
import service.ServiceException;
import service.patient.TreatmentService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TreatmentDoneAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = null;
        Integer urlId = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            try {
                TreatmentService service = getServiceFactory().getTreatmentService();
                Treatment treatment = service.readInfo(id);
                urlId = treatment.getPatient().getId();
                User user = (User)req.getSession(false).getAttribute("currentUser");
                treatment.setPerformer(user);
                service.done(treatment);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        //TODO: correct forward
        if (urlId != null) {
            return new Forward("/patient/view/treatment/list.html?id=" + urlId);
        } else {
            return new Forward("/patient/list.html");
        }
    }
}
