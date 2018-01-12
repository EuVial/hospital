package service.patient;

import domain.patient.Diagnosis;
import service.EntityService;
import service.ServiceException;

public interface DiagnosisService extends EntityService<Diagnosis> {
    Integer getIdByTitle(String title) throws ServiceException;
}
