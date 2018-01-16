package controller.patient;

import controller.Action;
import controller.Forward;
import controller.patient.view.treatment.TreatmentListAction;
import domain.patient.Diagnosis;
import domain.patient.Patient;
import org.apache.log4j.Logger;
import service.ServiceException;
import service.patient.DiagnosisService;
import service.patient.PatientService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PatientDischargeAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(TreatmentListAction.class));

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer patientId = null;
        try {
            patientId = Integer.parseInt(req.getParameter("patientId"));
        } catch (NumberFormatException e) {}

        if (patientId != null) {
            try {
                PatientService patientService = getServiceFactory().getPatientService();
                Patient patient = patientService.findById(patientId);
                req.setAttribute("patient", patient);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("PatientDischargeAction problem with services" + e);
                throw new ServletException(e);
            }
        }

        try {
            DiagnosisService diagnosisService = getServiceFactory().getDiagnosisService();
            List<Diagnosis> diagnoses = diagnosisService.findAll();
            req.setAttribute("diagnoses", diagnoses);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
