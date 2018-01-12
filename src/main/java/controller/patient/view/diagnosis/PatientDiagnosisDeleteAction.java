package controller.patient.view.diagnosis;

import controller.Action;
import controller.Forward;
import domain.patient.DiagnosisToPatient;
import service.ServiceException;
import service.patient.DiagnosisToPatientService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PatientDiagnosisDeleteAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer urlId = null;
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            try {
                DiagnosisToPatientService service = getServiceFactory().getDiagnosisToPatientService();
                DiagnosisToPatient diagnosisToPatient = service.findById(id);
                urlId = diagnosisToPatient.getPatient().getId();
                service.delete(diagnosisToPatient);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        if (urlId != null) {
            return new Forward("/patient/view.html?id=" + urlId);
        } else {
            return new Forward("/patient/list.html");
        }
    }
}