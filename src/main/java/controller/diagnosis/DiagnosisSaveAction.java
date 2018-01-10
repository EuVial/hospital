package controller.diagnosis;

import controller.Action;
import controller.Forward;
import domain.patient.Diagnosis;
import service.ServiceException;
import service.patient.DiagnosisService;
import util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DiagnosisSaveAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Diagnosis diagnosis = new Diagnosis();
        try {
            diagnosis.setId(Integer.parseInt(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        diagnosis.setTitle(req.getParameter("title"));
        if (diagnosis.getTitle() != null) {
            try {
                DiagnosisService service = getServiceFactory().getDiagnosisService();
                service.save(diagnosis);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/diagnosis/list.html");
    }
}
