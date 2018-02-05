package service.logic;

import dao.PersistException;
import dao.mysql.patient.MySqlDiagnosisToPatientDao;
import dao.mysql.patient.MySqlPatientDao;
import dao.mysql.patient.MySqlTreatmentDao;
import domain.patient.DiagnosisToPatient;
import domain.patient.Patient;
import domain.patient.Treatment;
import service.EntityNotExistsException;
import service.ServiceException;
import service.patient.PatientService;

import java.util.List;

public class PatientServiceImpl implements PatientService {
    //TODO: LOGGER
    private MySqlPatientDao patientDao;
    private MySqlDiagnosisToPatientDao diagnosisToPatientDao;
    private MySqlTreatmentDao treatmentDao;

    public void setPatientDao(final MySqlPatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public void setDiagnosisToPatientDao(final MySqlDiagnosisToPatientDao diagnosisToPatientDao) {
        this.diagnosisToPatientDao = diagnosisToPatientDao;
    }

    public void setTreatmentDao(final MySqlTreatmentDao treatmentDao) {
        this.treatmentDao = treatmentDao;
    }

    @Override
    public Patient findById(final Integer id) throws ServiceException {
        try {
            Patient patient = patientDao.read(id);
            if (patient != null) {
                List<DiagnosisToPatient> history = diagnosisToPatientDao.readHistory(id);
                Patient currentPatient;
                for (DiagnosisToPatient diagnosisToPatient : history) {
                    currentPatient = diagnosisToPatient.getPatient();
                    List<Treatment> treatmentHistory = treatmentDao
                            .readTreatmentsFromDiagnosisToPatient(diagnosisToPatient.getId());
                    if (treatmentHistory != null) {
                        diagnosisToPatient.setHistory(treatmentHistory);
                    }
                    if (currentPatient != null && currentPatient.getId() != null) {
                        currentPatient = patientDao.read(currentPatient.getId());
                        diagnosisToPatient.setPatient(currentPatient);
                    }
                }
                patient.setHistory(history);
            }
            return patient;
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Patient> findAll() throws ServiceException {
        try {
            return patientDao.getAll();
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(final Patient patient) throws ServiceException {
        try {
            if (patient.getId() != null) {
                Patient storedPatient = patientDao.read(patient.getId());
                if (storedPatient != null) {
                    patientDao.update(patient);
                } else {
                    throw new EntityNotExistsException(patient.getId());
                }
            } else {
                patientDao.persist(patient);
            }
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean canDelete(final Patient patient) throws ServiceException {
        try {
            return !patientDao.isInitiatesTransfers(patient);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(final Patient patient) throws ServiceException {
        if (patient.getId() != null) {
            try {
                patientDao.delete(patient);
            } catch (PersistException e) {
                throw new ServiceException(e);
            }
        }
    }
}
