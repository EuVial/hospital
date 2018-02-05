package controller.patient;

import constants.Constants;
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

public class PatientSaveAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(PatientSaveAction.class));

    @Override
    public Forward execute(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        Patient patient = new Patient();
        try {
            patient.setId(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException ignored) { }
        patient.setFirstName(req.getParameter("first_name"));
        patient.setLastName(req.getParameter("last_name"));
        patient.setWard(Integer.valueOf(req.getParameter("ward")));

        if (patient.getWard() < 1) {
            return new Forward("/patient/edit.html?id=" + patient.getId()
                    + "&message=patient.view.error.message.wrong.ward.number");
        }

        if (patient.getFirstName() != null
                && patient.getLastName() != null
                && patient.getFirstName().matches(Constants.REGEX_NAME)
                && patient.getLastName().matches(Constants.REGEX_NAME)
                && patient.getWard() != null) {
            try {
                PatientService service = getServiceFactory().getPatientService();
                service.save(patient);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("PatientSaveAction problem with services " + e);
                throw new ServletException(e);
            }
        } else {
            return new Forward("/patient/edit.html?id=" + patient.getId()
                    + "&message=message.incorrect.data");
        }
        return new Forward("/patient/view.html?id=" + patient.getId());
    }
}
