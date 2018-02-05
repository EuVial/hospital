package controller.patient;

import controller.Action;
import controller.Forward;
import domain.patient.Patient;
import org.apache.log4j.Logger;
import service.ServiceException;
import service.patient.PatientService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PatientDeleteAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(PatientDeleteAction.class));

    @Override
    public Forward execute(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException ignored) { }
        if (id != null) {
            try {
                PatientService service = getServiceFactory().getPatientService();
                Patient patient = service.findById(id);
                service.delete(patient);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("PatientDeleteAction problem with services" + e);
                throw new ServletException(e);
            }
        }
        return new Forward("/patient/list.html");
    }
}
