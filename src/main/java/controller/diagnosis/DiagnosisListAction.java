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
import java.util.List;

public class DiagnosisListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DiagnosisService service = getServiceFactory().getDiagnosisService();
            List<Diagnosis> diagnoses = service.findAll();
            req.setAttribute("diagnoses", diagnoses);
            return null;
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
