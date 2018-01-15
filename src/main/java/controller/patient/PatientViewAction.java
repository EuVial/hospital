package controller.patient;

import controller.Action;
import controller.Forward;
import domain.patient.Patient;
import service.ServiceException;
import service.patient.PatientService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PatientViewAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            PatientService service = getServiceFactory().getPatientService();
            Patient patient = service.findById(id);
            if (patient != null) {
                req.setAttribute("patient", patient);
                return null;
            } else {
                throw new NumberFormatException();
            }
        } catch(NumberFormatException e) {
            return new Forward("/patient/list.html");
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
