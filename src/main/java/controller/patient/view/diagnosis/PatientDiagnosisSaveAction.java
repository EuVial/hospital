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
        Integer patientId = null;
        Integer patientDiagnosisId = null;
        DiagnosisToPatientService diagnosisToPatientService = null;

        try {
            patientDiagnosisId = Integer.parseInt(req.getParameter("id"));
        } catch(NumberFormatException e) {}

        try {
            patientId = Integer.parseInt(req.getParameter("patientId"));
        } catch(NumberFormatException e) {}

        try {
            diagnosisToPatientService = getServiceFactory().getDiagnosisToPatientService();
        } catch (FactoryException e) {
            throw new ServletException(e);
        }

        String title = req.getParameter("diagnosis.title");
        if (patientDiagnosisId != null) {
//            diagnosisToPatient.setId(patientDiagnosisId);
            try {
                diagnosisToPatient = diagnosisToPatientService.findById(patientDiagnosisId);
            } catch(ServiceException e) {
                throw new ServletException(e);
            }
        }

        diagnosisToPatient.setPatient(new Patient());
        diagnosisToPatient.getPatient().setId(patientId);

        try {
            DiagnosisService diagnosisService = getServiceFactory().getDiagnosisService();
            diagnosisToPatient.setDiagnosis(new Diagnosis());
            diagnosisToPatient.getDiagnosis().setTitle(title);
            diagnosisToPatient.getDiagnosis().setId(diagnosisService.getIdByTitle(title));

            User user = (User)req.getSession(false).getAttribute("currentUser");
            diagnosisToPatient.setDoctor(user);

            if (diagnosisToPatient.getConsultationDate() == null) {
                diagnosisToPatient.setConsultationDate(new Date());
            }
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }

        if (    diagnosisToPatient.getPatient().getId() != null &&
                diagnosisToPatient.getDiagnosis().getId() != null &&
                diagnosisToPatient.getDoctor().getId() != null) {
            try {
                diagnosisToPatientService.save(diagnosisToPatient);
            } catch(ServiceException e) {
                throw new ServletException(e);
            }
        }

        if (patientDiagnosisId != null) {
            return new Forward("/patient/view/diagnosis/view.html?id=" + patientDiagnosisId);
        } else {
            return new Forward("/patient/view/disease_history.html?id=" + patientId);
        }
    }
}