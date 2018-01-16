package controller.patient;

import controller.Action;
import controller.Forward;
import domain.patient.Patient;
import service.ServiceException;
import service.patient.PatientService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PatientSaveAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Patient patient = new Patient();
        try {
            patient.setId(Integer.parseInt(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        patient.setFirstName(req.getParameter("first_name"));
        patient.setLastName(req.getParameter("last_name"));
        patient.setWard(Integer.valueOf(req.getParameter("ward")));

        if (patient.getWard() < 1) {
            return new Forward("/patient/edit.html?id=" + patient.getId() +
                    "&message=wrong.number");
        }

        if (    patient.getFirstName() != null &&
                patient.getLastName() != null &&
                patient.getWard() != null) {
            try {
                PatientService service = getServiceFactory().getPatientService();
                service.save(patient);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/patient/view.html?id=" + patient.getId());
    }
}
