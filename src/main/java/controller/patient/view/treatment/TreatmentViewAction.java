package controller.patient.view.treatment;

import controller.Action;
import controller.Forward;
import domain.patient.Patient;
import domain.patient.Treatment;
import service.ServiceException;
import service.patient.PatientService;
import service.patient.TreatmentService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TreatmentViewAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Integer patientId = Integer.parseInt(req.getParameter("patientId"));
            TreatmentService treatmentService = getServiceFactory().getTreatmentService();
            Treatment treatment = treatmentService.findById(id);
            if (treatment.getIsDone()) {
                treatment = treatmentService.readInfoIfDone(id);
            } else {
                treatment = treatmentService.readInfo(id);
            }
            PatientService patientService =getServiceFactory().getPatientService();
            Patient patient = patientService.findById(patientId);
            if (patient != null) {
                req.setAttribute("patient", patient);
                req.setAttribute("treatment", treatment);
                return null;
            } else {
                throw new NumberFormatException();
            }
        } catch(NumberFormatException e) {
            return new Forward("/patient/list.html");
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}