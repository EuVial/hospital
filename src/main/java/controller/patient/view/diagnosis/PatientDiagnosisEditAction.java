package controller.patient.view.diagnosis;

import controller.Action;
import controller.Forward;
import domain.patient.Diagnosis;
import domain.patient.DiagnosisToPatient;
import domain.patient.Patient;
import service.ServiceException;
import service.patient.DiagnosisService;
import service.patient.DiagnosisToPatientService;
import service.patient.PatientService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PatientDiagnosisEditAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = null;
        Integer patientId = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        try {
            patientId = Integer.parseInt(req.getParameter("patientId"));
        } catch(NumberFormatException e) {}

        if (patientId != null) {
            try {
                PatientService patientService = getServiceFactory().getPatientService();;
                Patient patient = patientService.findById(patientId);
                req.setAttribute("patient", patient);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }

        if (id != null) {
            try {
                DiagnosisToPatientService service = getServiceFactory().getDiagnosisToPatientService();
                DiagnosisToPatient diagnosisToPatient = service.readInfo(id);
                Patient patient = diagnosisToPatient.getPatient();
                if (patient != null) {
                    req.setAttribute("patient", patient);
                }
                req.setAttribute("patientDiagnosis", diagnosisToPatient);
                boolean patientDiagnosisCanBeDeleted = service.canDelete(diagnosisToPatient);
                req.setAttribute("patientDiagnosisCanBeDeleted", patientDiagnosisCanBeDeleted);

                String previousDiagnosisTitle = diagnosisToPatient.getDiagnosis().getTitle();
                req.setAttribute("previousDiagnosisTitle", previousDiagnosisTitle);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }

        try {
            DiagnosisService diagnosisService = getServiceFactory().getDiagnosisService();
            List<Diagnosis> diagnoses = diagnosisService.findAll();
            req.setAttribute("diagnoses", diagnoses);
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }

        return null;
    }
}
