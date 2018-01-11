package util;

import dao.datasource.DataSource;
import dao.mysql.patient.MySqlDiagnosisDao;
import dao.mysql.patient.MySqlDiagnosisToPatientDao;
import dao.mysql.patient.MySqlPatientDao;
import dao.mysql.patient.MySqlTreatmentDao;
import dao.mysql.user.MySqlUserDao;
import service.logic.*;
import service.patient.DiagnosisService;
import service.patient.DiagnosisToPatientService;
import service.patient.PatientService;
import service.patient.TreatmentService;
import service.user.UserService;

import java.sql.Connection;

public class MainServiceFactoryImpl implements ServiceFactory {
    private Connection connection;

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setDefaultPassword("12345");
        userService.setUserDao(getUserDao());
        return userService;
    }

    @Override
    public MySqlUserDao getUserDao() throws FactoryException {
        return new MySqlUserDao(getConnection());
    }

    @Override
    public PatientService getPatientService() throws FactoryException {
        PatientServiceImpl patientService = new PatientServiceImpl();
        patientService.setPatientDao(getPatientDao());
        patientService.setDiagnosisToPatientDao(getDiagnosisToPatientDao());
        patientService.setTreatmentDao(getTreatmentDao());
        return patientService;
    }

    @Override
    public MySqlPatientDao getPatientDao() throws FactoryException {
        return new MySqlPatientDao(getConnection());
    }

    @Override
    public TreatmentService getTreatmentService() throws FactoryException {
        TreatmentServiceImpl treatmentService = new TreatmentServiceImpl();
        treatmentService.setTreatmentDao(getTreatmentDao());
        return treatmentService;
    }

    @Override
    public MySqlTreatmentDao getTreatmentDao() throws FactoryException {
        return new MySqlTreatmentDao(getConnection());
    }

    @Override
    public DiagnosisService getDiagnosisService() throws FactoryException {
        DiagnosisServiceImpl diagnosisService = new DiagnosisServiceImpl();
        diagnosisService.setDiagnosisDao(getDiagnosisDao());
        return diagnosisService;
    }

    @Override
    public MySqlDiagnosisDao getDiagnosisDao() throws FactoryException {
        return new MySqlDiagnosisDao(getConnection());
    }

    @Override
    public DiagnosisToPatientService getDiagnosisToPatientService() throws FactoryException {
        DiagnosisToPatientServiceImpl diagnosisToPatientService = new DiagnosisToPatientServiceImpl();
        diagnosisToPatientService.setDiagnosisToPatientDao(getDiagnosisToPatientDao());
        diagnosisToPatientService.setTreatmentDao(getTreatmentDao());
        return diagnosisToPatientService;
    }

    @Override
    public MySqlDiagnosisToPatientDao getDiagnosisToPatientDao() throws FactoryException {
        return new MySqlDiagnosisToPatientDao(getConnection());
    }

    @Override
    public Connection getConnection() throws FactoryException {
        if(connection == null) {
            connection = DataSource.getInstance().getConnection();
        }
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
            connection = null;
        } catch(Exception ignored) {}
    }
}