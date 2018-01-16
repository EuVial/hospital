package controller.patient.view.diagnosis;

import controller.Action;
import controller.Forward;
import domain.patient.DiagnosisToPatient;
import domain.patient.Patient;
import org.apache.log4j.Logger;
import service.ServiceException;
import service.patient.DiagnosisToPatientService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PatientDiagnosisViewAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(PatientDiagnosisViewAction.class));
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            DiagnosisToPatientService service = getServiceFactory().getDiagnosisToPatientService();
            DiagnosisToPatient diagnosisToPatient = service.readInfo(id);
            Patient patient = diagnosisToPatient.getPatient();
            if (patient != null) {
                req.setAttribute("patient", patient);
            }
            req.setAttribute("patientDiagnosis", diagnosisToPatient);
            return null;
        } catch (NumberFormatException e) {
            return new Forward("/patient/list.html");
        } catch (FactoryException | ServiceException e) {
            LOGGER.error("PatientDiagnosisViewAction problem with services");
            throw new ServletException(e);
        }
    }
}