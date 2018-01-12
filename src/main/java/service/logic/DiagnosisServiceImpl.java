package service.logic;

import dao.PersistException;
import dao.mysql.patient.MySqlDiagnosisDao;
import domain.patient.Diagnosis;
import service.EntityNotExistsException;
import service.ServiceException;
import service.patient.DiagnosisService;

import java.util.List;

public class DiagnosisServiceImpl implements DiagnosisService {
    //TODO: LOGGER
    private MySqlDiagnosisDao diagnosisDao;

    public void setDiagnosisDao(MySqlDiagnosisDao diagnosisDao) {
        this.diagnosisDao = diagnosisDao;
    }

    @Override
    public Diagnosis findById(Integer id) throws ServiceException {
        try {
            return diagnosisDao.read(id);
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Diagnosis> findAll() throws ServiceException {
        try {
            return diagnosisDao.getAll();
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Diagnosis diagnosis) throws ServiceException {
        try {
            if(diagnosis.getId() != null) {
                Diagnosis storedDiagnosis = diagnosisDao.read(diagnosis.getId());
                if(storedDiagnosis != null) {
                    diagnosisDao.update(diagnosis);
                } else {
                    throw new EntityNotExistsException(diagnosis.getId());
                }
            } else {
                diagnosisDao.persist(diagnosis);
            }
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean canDelete(Diagnosis diagnosis) throws ServiceException {
        try {
            return !diagnosisDao.isInitiatesTransfers(diagnosis);
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Diagnosis diagnosis) throws ServiceException {
        if(diagnosis.getId() != null) {
            try {
                diagnosisDao.delete(diagnosis);
            } catch (PersistException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public Integer getIdByTitle(String title) throws ServiceException {
        try {
            return diagnosisDao.getIdByTitle(title);
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }
}
