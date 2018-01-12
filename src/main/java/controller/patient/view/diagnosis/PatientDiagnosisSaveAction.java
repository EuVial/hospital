package controller.patient.view.diagnosis;

import controller.Action;
import controller.Forward;
import domain.patient.Diagnosis;
import domain.patient.DiagnosisToPatient;
import domain.patient.Patient;
import domain.user.User;
import service.ServiceException;
import service.patient.DiagnosisService;
import service.patient.DiagnosisToPatientService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class PatientDiagnosisSaveAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiagnosisToPatient diagnosisToPatient = new DiagnosisToPatient();
        Integer urlId = null;
        try {
            diagnosisToPatient.setId(Integer.parseInt(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        diagnosisToPatient.setDiagnosis(new Diagnosis());
        String title = req.getParameter("diagnosis.title");
        diagnosisToPatient.getDiagnosis().setTitle(title);
        diagnosisToPatient.setPatient(new Patient());
        diagnosisToPatient.getPatient().setId(Integer.valueOf(req.getParameter("patientId")));
//        diagnosisToPatient.getDiagnosis().setId(Integer.valueOf(req.getParameter("diagnosis.id")));
        if (    diagnosisToPatient.getDiagnosis().getTitle() != null &&
                diagnosisToPatient.getPatient().getId() != null) {
            try {
                urlId = diagnosisToPatient.getPatient().getId();
                DiagnosisToPatientService diagnosisToPatientService = getServiceFactory().getDiagnosisToPatientService();
                DiagnosisService diagnosisService = getServiceFactory().getDiagnosisService();
                User user = (User)req.getSession(false).getAttribute("currentUser");
                diagnosisToPatient.setDoctor(user);
                if (diagnosisToPatient.getConsultationDate() == null) {
                    diagnosisToPatient.setConsultationDate(new Date());
                }
                diagnosisToPatient.getDiagnosis().setId(diagnosisService.getIdByTitle(title));
                diagnosisToPatientService.save(diagnosisToPatient);
            } catch(FactoryException | ServiceException e) {
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