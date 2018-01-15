package service.logic;

import dao.PersistException;
import dao.mysql.patient.MySqlDiagnosisToPatientDao;
import dao.mysql.patient.MySqlPatientDao;
import dao.mysql.patient.MySqlTreatmentDao;
import domain.patient.Treatment;
import service.EntityNotExistsException;
import service.ServiceException;
import service.patient.TreatmentService;

import java.util.List;

public class TreatmentServiceImpl implements TreatmentService {
    //TODO: LOGGER
    private MySqlTreatmentDao treatmentDao;

    private MySqlPatientDao patientDao;

    private MySqlDiagnosisToPatientDao diagnosisToPatientDao;

    public void setTreatmentDao(MySqlTreatmentDao treatmentDao) {
        this.treatmentDao = treatmentDao;
    }

    public void setPatientDao(MySqlPatientDao patientDao) { this.patientDao = patientDao; }

    public void setDiagnosisToPatientDao(MySqlDiagnosisToPatientDao diagnosisToPatientDao) {
        this.diagnosisToPatientDao = diagnosisToPatientDao;
    }

    @Override
    public Treatment findById(Integer id) throws ServiceException {
        try {
            return treatmentDao.read(id);
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Treatment> findAll() throws ServiceException {
        try {
            return treatmentDao.getAll();
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Treatment treatment) throws ServiceException {
        try {
            if(treatment.getId() != null) {
                Treatment storedTreatment = treatmentDao.read(treatment.getId());
                if(storedTreatment != null) {
                    treatmentDao.update(treatment);
                } else {
                    throw new EntityNotExistsException(treatment.getId());
                }
            } else {
                treatmentDao.persist(treatment);
            }
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean canDelete(Treatment treatment) throws ServiceException {
        return true;
    }

    @Override
    public void delete(Treatment treatment) throws ServiceException {
        if(treatment.getId() != null) {
            try {
                treatmentDao.delete(treatment);
            } catch (PersistException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public Treatment readInfo(Integer treatmentId) throws ServiceException {
        try {
            return treatmentDao.readInfo(treatmentId);
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Treatment readInfoIfDone(Integer treatmentId) throws ServiceException {
        try {
            return treatmentDao.readInfoIfDone(treatmentId);
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void done(Treatment treatment) throws ServiceException {
        if(treatment.getId() != null) {
            try {
                treatmentDao.done(treatment);
            } catch (PersistException e) {
                throw new ServiceException(e);
            }
        }
    }
}
