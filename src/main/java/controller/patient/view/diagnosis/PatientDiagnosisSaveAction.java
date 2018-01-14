package controller.patient.view.diagnosis;

import controller.Action;
import controller.Forward;
import domain.patient.Diagnosis;
import domain.patient.DiagnosisToPatient;
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
        String title = req.getParameter("diagnosis.title");
//        diagnosisToPatient.getPatient().setId(Integer.valueOf(req.getParameter("patientId")));
//        diagnosisToPatient.getDiagnosis().setId(Integer.valueOf(req.getParameter("diagnosis.id")));
        if (title != null) {
            try {
                DiagnosisToPatientService diagnosisToPatientService = getServiceFactory().getDiagnosisToPatientService();
                DiagnosisService diagnosisService = getServiceFactory().getDiagnosisService();
                diagnosisToPatient = diagnosisToPatientService.readInfo(diagnosisToPatient.getId());
                diagnosisToPatient.setDiagnosis(new Diagnosis());
                diagnosisToPatient.getDiagnosis().setTitle(title);

                User user = (User)req.getSession(false).getAttribute("currentUser");
                diagnosisToPatient.setDoctor(user);

                if (diagnosisToPatient.getConsultationDate() == null) {
                    diagnosisToPatient.setConsultationDate(new Date());
                }
                diagnosisToPatient.getDiagnosis().setId(diagnosisService.getIdByTitle(title));
                diagnosisToPatientService.save(diagnosisToPatient);

                urlId = diagnosisToPatient.getId();
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }

        if (urlId != null) {
            return new Forward("/patient/view/diagnosis/view.html?id=" + urlId);
        } else {
            return new Forward("/patient/list.html");
        }
    }
}