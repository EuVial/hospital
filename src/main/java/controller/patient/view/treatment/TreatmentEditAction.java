package controller.patient.view.treatment;

import controller.Action;
import controller.Forward;
import domain.patient.Patient;
import domain.patient.Treatment;
import domain.patient.TreatmentType;
import service.ServiceException;
import service.patient.PatientService;
import service.patient.TreatmentService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TreatmentEditAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = null;
        Treatment treatment;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            try {
                TreatmentService treatmentService = getServiceFactory().getTreatmentService();
                treatment = treatmentService.readInfo(id);
                Patient patient = treatment.getPatient();
                PatientService patientService = getServiceFactory().getPatientService();
                patient = patientService.findById(patient.getId());
                if(patient != null) {
                    req.setAttribute("patient", patient);
                }
                req.setAttribute("treatment", treatment);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("types", TreatmentType.values());
        return null;
    }
}