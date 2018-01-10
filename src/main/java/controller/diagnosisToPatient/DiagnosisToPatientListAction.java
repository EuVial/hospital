//package controller.diagnosisToPatient;
//
//import controller.Action;
//import controller.Forward;
//import domain.patient.DiagnosisToPatient;
//import service.ServiceException;
//import service.patient.DiagnosisToPatientService;
//import util.FactoryException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//public class DiagnosisToPatientListAction extends Action {
//    @Override
//    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            DiagnosisToPatientService diagnosisToPatientService = getServiceFactory().getDiagnosisToPatientService();
//            List<DiagnosisToPatient> diagnosisToPatients = diagnosisToPatientService.findAll();
//            req.setAttribute("diagnosisToPatient", diagnosisToPatients);
//            return null;
//        } catch(FactoryException | ServiceException e) {
//            throw new ServletException(e);
//        }
//    }
//}
