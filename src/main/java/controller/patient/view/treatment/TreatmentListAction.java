package controller.patient.view.treatment;

import controller.Action;
import controller.Forward;
import domain.patient.DiagnosisToPatient;
import domain.patient.Patient;
import domain.patient.Treatment;
import service.ServiceException;
import service.patient.PatientService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TreatmentListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            PatientService service = getServiceFactory().getPatientService();
            Patient patient = service.findById(id);
            List<Treatment> treatments = new ArrayList<>();
            if (patient != null) {
                req.setAttribute("patient", patient);
                List<DiagnosisToPatient> patientDiagnoses = patient.getHistory();
                if (patientDiagnoses != null) {
                    for (DiagnosisToPatient diagnosisToPatient : patientDiagnoses) {
                        for (Treatment treatment : diagnosisToPatient.getHistory()) {
                            treatment.setDiagnosisToPatient(diagnosisToPatient);
                            if (!treatment.getIsDone())
                                treatments.add(treatment);
                        }
                    }
                }
//                TreatmentService treatmentService = getServiceFactory().getTreatmentService();
//                for (Treatment treatment : treatmentsData) {
//                    treatment = treatmentService.readInfo(treatment.getId());
//                    if (!treatment.getIsDone()) treatments.add(treatment);
//                }
                req.setAttribute("treatments", treatments);
                return null;
            } else {
                throw new NumberFormatException();
            }
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}