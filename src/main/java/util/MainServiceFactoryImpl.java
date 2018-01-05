package util;

import dao.datasource.DataSource;
import dao.mysql.patient.MySqlPatientDao;
import dao.mysql.user.MySqlUserDao;
import service.logic.PatientServiceImpl;
import service.logic.UserServiceImpl;
import service.patient.PatientService;
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
        MySqlUserDao userDao = new MySqlUserDao(getConnection());
        return userDao;
    }

    @Override
    public PatientService getPatientService() throws FactoryException {
        PatientServiceImpl patientService = new PatientServiceImpl();
        patientService.setPatientDao(getPatientDao());
        return patientService;
    }

    @Override
    public MySqlPatientDao getPatientDao() throws FactoryException {
        MySqlPatientDao patientDao = new MySqlPatientDao(getConnection());
        return patientDao;
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
        } catch(Exception e) {}
    }
}