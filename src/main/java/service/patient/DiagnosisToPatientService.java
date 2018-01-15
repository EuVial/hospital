package service.patient;

import domain.patient.DiagnosisToPatient;
import service.EntityService;
import service.ServiceException;

public interface DiagnosisToPatientService extends EntityService<DiagnosisToPatient> {
    DiagnosisToPatient readInfo(Integer diagnosisToPatientId) throws ServiceException;

    Integer getDiagnosisToPatientId(String diagnosisTitle, Integer patientId) throws ServiceException;

    String readDiagnosisTitle(Integer diagnosisToPatientId) throws ServiceException;
}
