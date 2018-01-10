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

public class DiagnosisEditAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            try {
                DiagnosisService service = getServiceFactory().getDiagnosisService();
                Diagnosis diagnosis = service.findById(id);
                req.setAttribute("diagnosis", diagnosis);
                boolean diagnosisCanBeDeleted = service.canDelete(diagnosis);
                req.setAttribute("diagnosisCanBeDeleted", diagnosisCanBeDeleted);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}