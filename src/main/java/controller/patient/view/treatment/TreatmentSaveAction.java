package controller.patient.view.treatment;

import controller.Action;
import controller.Forward;
import domain.patient.Treatment;
import domain.patient.TreatmentType;
import domain.user.User;
import service.ServiceException;
import service.patient.TreatmentService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TreatmentSaveAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Treatment treatment = new Treatment();
        Integer urlId = null;
        Integer id = null;
        String patientDiagnosisTitle = null;
        Integer patientId = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
            patientDiagnosisTitle = req.getParameter("diagnosis_title");
        } catch(NumberFormatException e) {}
        treatment.setDone(false);
        if(id != null) {
            try {
                TreatmentService service = getServiceFactory().getTreatmentService();
                treatment = service.readInfo(id);
                urlId = treatment.getPatient().getId();
                User user = (User) req.getSession(false).getAttribute("currentUser");
                treatment.setPerformer(user);
                treatment.getDiagnosisToPatient().getDiagnosis().setTitle(patientDiagnosisTitle);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        treatment.setTitle(req.getParameter("title"));
        try {
            treatment.setType(TreatmentType.values()[Integer.parseInt(req.getParameter("type"))]);
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {}

        if(     treatment.getTitle() != null &&
                treatment.getDiagnosisToPatient().getId() != null &&
                treatment.getType() != null) {
            try {
                TreatmentService service = getServiceFactory().getTreatmentService();
                service.save(treatment);
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
