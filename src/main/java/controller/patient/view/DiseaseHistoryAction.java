package controller.patient.view;

import controller.Action;
import controller.Forward;
import domain.patient.DiagnosisToPatient;
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

public class DiseaseHistoryAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(DiseaseHistoryAction.class));
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            PatientService service = getServiceFactory().getPatientService();
            Patient patient = service.findById(id);
            List<DiagnosisToPatient> patientDiagnoses;
            if (patient != null) {
                req.setAttribute("patient", patient);
                patientDiagnoses = patient.getHistory();
                req.setAttribute("patientDiagnoses", patientDiagnoses);
                return null;
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return new Forward("/patient/list.html");
        } catch (FactoryException | ServiceException e) {
            LOGGER.error("DiseaseHistoryAction problem with services" + e);
            throw new ServletException(e);
        }
    }
}