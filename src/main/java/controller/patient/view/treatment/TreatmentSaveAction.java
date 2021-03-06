package controller.patient.view.treatment;

import controller.Action;
import controller.Forward;
import domain.patient.DiagnosisToPatient;
import domain.patient.Patient;
import domain.patient.Treatment;
import domain.patient.TreatmentType;
import domain.user.User;
import org.apache.log4j.Logger;
import service.ServiceException;
import service.patient.DiagnosisToPatientService;
import service.patient.TreatmentService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TreatmentSaveAction extends Action {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(TreatmentSaveAction.class));

    @Override
    public Forward execute(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        Treatment treatment = new Treatment();
        String patientDiagnosisTitle;
        Integer patientId = null;
        Integer patientDiagnosisId = null;
        try {
            treatment.setId(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException ignored) { }
        try {
            patientId = Integer.parseInt(req.getParameter("patientId"));
        } catch (NumberFormatException ignored) { }
        try {
            patientDiagnosisId = Integer.parseInt(req.getParameter("patientDiagnosisId"));
        } catch (NumberFormatException ignored) { }

        treatment.setPatient(new Patient());
        treatment.getPatient().setId(patientId);
        patientDiagnosisTitle = req.getParameter("diagnosisTitle");
        treatment.setTitle(req.getParameter("title"));
        try {
            treatment.setType(TreatmentType.values()[Integer.parseInt(req.getParameter("type"))]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) { }
        treatment.setDone(false);
        User user = (User) req.getSession(false).getAttribute("currentUser");
        treatment.setPerformer(user);
        treatment.setDiagnosisToPatient(new DiagnosisToPatient());
        if (patientDiagnosisId != null) {
            treatment.getDiagnosisToPatient().setId(patientDiagnosisId);
        } else {
            try {
                DiagnosisToPatientService diagnosisToPatientService = getServiceFactory().getDiagnosisToPatientService();
                treatment.getDiagnosisToPatient().setId(diagnosisToPatientService.getDiagnosisToPatientId(patientDiagnosisTitle, patientId));
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("TreatmentSaveAction problem with services " + e);
                throw new ServletException(e);
            }
        }

        if (treatment.getTitle() != null
                && treatment.getDiagnosisToPatient().getId() != null
                && treatment.getType() != null
                && treatment.getPerformer().getId() != null) {
            try {
                TreatmentService service = getServiceFactory().getTreatmentService();
                service.save(treatment);
            } catch (FactoryException | ServiceException e) {
                LOGGER.error("TreatmentSaveAction problem with services " + e);
                throw new ServletException(e);
            }
        }

        if (patientId != null) {
            return new Forward("/patient/view/disease_history.html?id=" + patientId);
        } else {
            return new Forward("/patient/list.html");
        }
    }
}
