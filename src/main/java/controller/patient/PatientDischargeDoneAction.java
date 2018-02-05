package controller.patient;

import controller.Action;
import controller.Forward;
import domain.patient.Diagnosis;
import domain.patient.DiagnosisToPatient;
import domain.patient.Patient;
import domain.user.User;
import org.apache.log4j.Logger;
import service.ServiceException;
import service.patient.DiagnosisService;
import service.patient.DiagnosisToPatientService;
import service.patient.PatientService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class PatientDischargeDoneAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(PatientDischargeDoneAction.class));

    @Override
    public Forward execute(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        DiagnosisToPatient diagnosisToPatient = new DiagnosisToPatient();
        Integer patientId = null;
        Integer patientDiagnosisId = null;
        DiagnosisToPatientService diagnosisToPatientService = null;

        try {
            patientDiagnosisId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException ignored) { }

        try {
            patientId = Integer.parseInt(req.getParameter("patientId"));
        } catch (NumberFormatException ignored) { }

        try {
            diagnosisToPatientService = getServiceFactory().getDiagnosisToPatientService();
        } catch (FactoryException e) {
            LOGGER.error("PatientDischargeDoneAction problem with services " + e);
            throw new ServletException(e);
        }

        String title = req.getParameter("diagnosis.title");
        if (patientDiagnosisId != null) {
            try {
                diagnosisToPatient = diagnosisToPatientService.findById(patientDiagnosisId);
            } catch (ServiceException e) {
                LOGGER.error("PatientDischargeDoneAction problem with services " + e);
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

            User user = (User) req.getSession(false).getAttribute("currentUser");
            diagnosisToPatient.setDoctor(user);

            if (diagnosisToPatient.getConsultationDate() == null) {
                Timestamp stamp = new Timestamp(System.currentTimeMillis());
                diagnosisToPatient.setConsultationDate(new Date(stamp.getTime()));
            }
        } catch (FactoryException | ServiceException e) {
            LOGGER.error("PatientDischargeDoneAction problem with services " + e);
            throw new ServletException(e);
        }

        if (diagnosisToPatient.getPatient().getId() != null
                && diagnosisToPatient.getDiagnosis().getId() != null
                && diagnosisToPatient.getDoctor().getId() != null) {
            try {
                diagnosisToPatientService.save(diagnosisToPatient);
            } catch (ServiceException e) {
                LOGGER.error("Can't save extended diagnosis " + e);
                throw new ServletException(e);
            }
        }
        try {
            PatientService patientService = getServiceFactory().getPatientService();
            Patient patient = patientService.findById(patientId);
            patient.setWard(null);
            patientService.save(patient);
        } catch (FactoryException | ServiceException e) {
            LOGGER.error("PatientDischargeDoneAction problem with services " + e);
            throw new ServletException(e);
        }

        if (patientDiagnosisId != null) {
            return new Forward("/patient/view/diagnosis/view.html?id=" + patientDiagnosisId);
        } else {
            return new Forward("/patient/view/disease_history.html?id=" + patientId);
        }
    }
}


