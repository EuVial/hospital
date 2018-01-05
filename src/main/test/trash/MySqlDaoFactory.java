//
//package com.epam.mysql;
//
//import com.epam.dao.DaoFactory;
//import com.epam.dao.GenericDao;
//import PersistException;
//import User;
//import MySqlUserDao;
//import org.apache.log4j.Logger;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MySqlDaoFactory implements DaoFactory<Connection> {
//    private final static Logger LOGGER = Logger.getLogger(String.valueOf(MySqlDaoFactory.class));
//    private final static String USERNAME = "root";
//    private final static String PASSWORD = "pass";
//    private final static String URL = "jdbc:mysql://localhost:3306/hospital?useSSL=false";
//    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    private Map<Class, DaoCreator> creators;
//
//    @Override
//    public Connection getContext() throws PersistException {
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            LOGGER.warn("Can't get connection to " + URL);
//            throw new PersistException(e);
//        }
//        return connection;
//    }
//
//    @Override
//    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
//        DaoCreator creator = creators.get(dtoClass);
//        if (creator == null) {
//            throw new PersistException("Dao object for " + dtoClass + " not found");
//        }
//        return creator.create(connection);
//    }
//
//    public MySqlDaoFactory() {
//        try {
//            Class.forName(JDBC_DRIVER);
//        } catch (ClassNotFoundException e) {
//            LOGGER.warn("Driver not found");
//        }
//        creators = new HashMap<>();
//        creators.put(User.class, new DaoCreator<Connection>() {
//            @Override
//            public GenericDao create(Connection connection) {
//                return (GenericDao) new MySqlUserDao(connection);
//            }
//        });
////        creators.put(Patient.class, new DaoCreator() {
////            @Override
////            public GenericDao create(Object o) {
////                return new MySqlPatientDao(connection);
////            }
////        });
//    }
//
//}
