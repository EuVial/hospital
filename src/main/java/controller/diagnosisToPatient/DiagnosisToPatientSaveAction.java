//package controller.diagnosisToPatient;
//
//import controller.Action;
//import controller.Forward;
//import domain.patient.DiagnosisToPatient;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class DiagnosisToPatientSaveAction extends Action {
//    @Override
//    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        DiagnosisToPatient diagnosisToPatient = new DiagnosisToPatient();
//        try {
//            diagnosisToPatient.setId(Integer.parseInt(req.getParameter("id")));
//        } catch(NumberFormatException e) {}
//        diagnosisToPatient.setPatient(req.getParameter("title"));
//        if (diagnosis.getTitle() != null) {
//            try {
//                DiagnosisService service = getServiceFactory().getDiagnosisService();
//                service.save(diagnosis);
//            } catch(FactoryException | ServiceException e) {
//                throw new ServletException(e);
//            }
//        }
//        return new Forward("/diagnosis/list.html");
//    }
//}