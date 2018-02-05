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
import java.util.List;

public class PatientListAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(PatientListAction.class));

    @Override
    public Forward execute(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            PatientService patientService = getServiceFactory().getPatientService();
            List<Patient> patients = patientService.findAll();
            req.setAttribute("patients", patients);
            return null;
        } catch (FactoryException | ServiceException e) {
            LOGGER.error("PatientListAction problem with services " + e);
            throw new ServletException(e);
        }
    }
}
