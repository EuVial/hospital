package controller.patient.view;

import controller.Action;
import controller.Forward;
import domain.patient.DiagnosisToPatient;
import domain.patient.Patient;
import service.ServiceException;
import service.patient.DiagnosisToPatientService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PatientDiagnosisAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            DiagnosisToPatientService service = getServiceFactory().getDiagnosisToPatientService();
            DiagnosisToPatient diagnosisToPatient = service.readInfo(id);
            Patient patient = diagnosisToPatient.getPatient();
            if(patient != null) {
                req.setAttribute("patient", patient);
            }
            if(diagnosisToPatient != null) {
                req.setAttribute("patientDiagnosis", diagnosisToPatient);
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