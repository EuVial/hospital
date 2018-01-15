package service.patient;

import domain.patient.Treatment;
import service.EntityService;
import service.ServiceException;

public interface TreatmentService extends EntityService<Treatment> {
    Treatment readInfo(Integer treatmentId) throws ServiceException;

    Treatment readInfoIfDone(Integer treatmentId) throws ServiceException;

    void done(Treatment treatment) throws ServiceException;
}
