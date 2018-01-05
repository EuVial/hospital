package service.patient;

import dao.entity.patient.Patient;
import service.ServiceException;

import java.util.List;

public interface PatientService {
    Patient findById(Integer id) throws ServiceException;

    List<Patient> findAll() throws ServiceException;

    void save(Patient patient) throws ServiceException;

    boolean canDelete(Patient patient) throws ServiceException;

    void delete(Patient patient) throws ServiceException;
}
