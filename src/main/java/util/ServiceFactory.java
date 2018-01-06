package util;

import dao.mysql.patient.MySqlPatientDao;
import dao.mysql.patient.MySqlTreatmentDao;
import dao.mysql.user.MySqlUserDao;
import service.patient.PatientService;
import service.patient.TreatmentService;
import service.user.UserService;

import java.sql.Connection;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService() throws FactoryException;
    PatientService getPatientService() throws FactoryException;
    TreatmentService getTreatmentService() throws FactoryException;

    MySqlUserDao getUserDao() throws FactoryException;
    MySqlPatientDao getPatientDao() throws FactoryException;
    MySqlTreatmentDao getTreatmentDao() throws FactoryException;

    Connection getConnection() throws FactoryException;
}
