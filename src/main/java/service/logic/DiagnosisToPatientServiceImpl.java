package service.logic;

import dao.PersistException;
import dao.mysql.patient.MySqlDiagnosisToPatientDao;
import dao.mysql.patient.MySqlTreatmentDao;
import domain.patient.DiagnosisToPatient;
import domain.patient.Treatment;
import service.EntityNotExistsException;
import service.ServiceException;
import service.patient.DiagnosisToPatientService;

import java.util.List;

public class DiagnosisToPatientServiceImpl implements DiagnosisToPatientService {
    //TODO: LOGGER
    private MySqlDiagnosisToPatientDao diagnosisToPatientDao;

    private MySqlTreatmentDao treatmentDao;

    public void setDiagnosisToPatientDao(MySqlDiagnosisToPatientDao diagnosisToPatientDao) {
        this.diagnosisToPatientDao = diagnosisToPatientDao;
    }

    public void setTreatmentDao(MySqlTreatmentDao treatmentDao) {
        this.treatmentDao = treatmentDao;
    }

    @Override
    public DiagnosisToPatient findById(Integer id) throws ServiceException {
        try {
            DiagnosisToPatient diagnosisToPatient = diagnosisToPatientDao.read(id);
            if (diagnosisToPatient != null) {
                List<Treatment> history = treatmentDao.readHistoryOfDiagnosisToPatient(id);
                DiagnosisToPatient currentDiagnosisToPatient;
                for (Treatment treatment : history) {
                    currentDiagnosisToPatient = treatment.getDiagnosisToPatient();
                    if (currentDiagnosisToPatient != null && currentDiagnosisToPatient.getId() != null) {
                        currentDiagnosisToPatient = diagnosisToPatientDao.read(currentDiagnosisToPatient.getId());
                        treatment.setDiagnosisToPatient(currentDiagnosisToPatient);
                    }
                }
                diagnosisToPatient.setHistory(history);
            }
            return diagnosisToPatient;
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<DiagnosisToPatient> findAll() throws ServiceException {
        try {
            return diagnosisToPatientDao.getAll();
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(DiagnosisToPatient diagnosisToPatient) throws ServiceException {
        try {
            if(diagnosisToPatient.getId() != null) {
                DiagnosisToPatient storedDiagnosis = diagnosisToPatientDao.read(diagnosisToPatient.getId());
                if(storedDiagnosis != null) {
                    diagnosisToPatientDao.update(diagnosisToPatient);
                } else {
                    throw new EntityNotExistsException(diagnosisToPatient.getId());
                }
            } else {
                diagnosisToPatientDao.persist(diagnosisToPatient);
            }
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean canDelete(DiagnosisToPatient diagnosisToPatient) throws ServiceException {
        try {
            return !diagnosisToPatientDao.isInitiatesTransfers(diagnosisToPatient);
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(DiagnosisToPatient diagnosis) throws ServiceException {
        if(diagnosis.getId() != null) {
            try {
                diagnosisToPatientDao.delete(diagnosis);
            } catch (PersistException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public DiagnosisToPatient readInfo(Integer diagnosisToPatientId) throws ServiceException {
        try {
            return diagnosisToPatientDao.readInfo(diagnosisToPatientId);
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }
}

