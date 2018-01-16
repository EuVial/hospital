package controller.patient.view.treatment;

import controller.Action;
import controller.Forward;
import domain.patient.*;
import org.apache.log4j.Logger;
import service.ServiceException;
import service.patient.DiagnosisToPatientService;
import service.patient.PatientService;
import service.patient.TreatmentService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TreatmentEditAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(TreatmentEditAction.class));
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = null;
        Integer patientId = null;
        Integer patientDiagnosisId = null;
        Treatment treatment;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException ignored) {}
        try {
            patientId = Integer.parseInt(req.getParameter("patientId"));
        } catch (NumberFormatException ignored) {}
        try {
            patientDiagnosisId = Integer.parseInt(req.getParameter("patientDiagnosisId"));
        } catch (NumberFormatException ignored) {}
        if (patientDiagnosisId != null) {
            try {
                treatment = new Treatment();
                DiagnosisToPatientService diagnosisToPatientService =
                        getServiceFactory().getDiagnosisToPatientService();
                String diagnosisTitle = diagnosisToPatientService.readDiagnosisTitle(patientDiagnosisId);
                treatment.setDiagnosisToPatient(new DiagnosisToPatient());
                treatment.getDiagnosisToPatient().setId(patientDiagnosisId);
                treatment.getDiagnosisToPatient().setDiagnosis(new Diagnosis());
                treatment.getDiagnosisToPatient().getDiagnosis().setTitle(diagnosisTitle);

                PatientService patientService = getServiceFactory().getPatientService();
                Patient patient = patientService.findById(patientId);

                treatment.setPatient(patient);
                if (patient != null) {
                    req.setAttribute("patient", patient);
                }
                req.setAttribute("treatment", treatment);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("TreatmentEditAction problem with services " + e);
                throw new ServletException(e);
            }
        }
        if (id != null) {
            try {
                TreatmentService treatmentService = getServiceFactory().getTreatmentService();
                treatment = treatmentService.readInfo(id);
                PatientService patientService = getServiceFactory().getPatientService();
                Patient patient = patientService.findById(patientId);
                if (patient != null) {
                    req.setAttribute("patient", patient);
                }
                req.setAttribute("treatment", treatment);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("TreatmentEditAction problem with services " + e);
                throw new ServletException(e);
            }
        }
        req.setAttribute("types", TreatmentType.values());
        return null;
    }
}