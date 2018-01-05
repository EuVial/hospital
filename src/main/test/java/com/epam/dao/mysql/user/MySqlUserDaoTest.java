//package com.epam.dao.mysql.user;
//
//import constants.Constants;
//import Dao;
//import Identified;
//import Connector;
//import User;
//import UserRole;
//import UserSpecialization;
//import PersistException;
//import org.apache.log4j.Logger;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.Serializable;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//
//public abstract class MySqlUserDaoTest {
//    private final static Logger LOGGER =
//            Logger.getLogger(String.valueOf(MySqlUserDaoTest.class));
//    private static Dao<Integer, User> userDao;
//    private static User user;
//    private Connection connection;
//
//    @Before
//    public void setUp() throws PersistException, SQLException {
//        Connector.init(constants.Constants.JDBC_DRIVER, constants.Constants.JDBC_URL, constants.Constants.JDBC_USER, constants.Constants.JDBC_PASSWORD);
//        try {
//            connection = Connector.getConnection();
//            userDao = new MySqlUserDao(connection);
//        } catch (SQLException e) {
//            LOGGER.warn("Cannot getConnection by JDBC driver");
//        }
//        user.setLogin("test_login");
//        user.setPassword("test_password");
//        user.setFirstName("test_first_name");
//        user.setLastName("test_last_name");
//        user.setRole(UserRole.values()[1]);
//        user.setSpecialization(new UserSpecialization());
//        user.getSpecialization().setId(1);
//    }
//
//    @After
//    public void tearDown() throws SQLException {
//        connection.rollback();
//        connection.close();
//    }
//
//    @Test
//    public void testCreate() throws PersistException {
//        User usertest = userDao.create();
//        Assert.assertNotNull(userDao.read(usertest.getId()));
//    }
//
//    @Test
//    public void testPersist() throws PersistException {
//        User usertest = new User();
//        Group group = (Group) factory.getDao(connection, Group.class).create();
//        student.setGroup(group);
//        group.setNumber(1234);
//        student = (Student) factory.getDao(connection, Student.class).persist(student);
//        Assert.assertNotNull("Group is null.", student.getGroup());
//        Assert.assertEquals("Wrong group number.", 1234, student.getGroup().getNumber());
//    }
//
//    @Test
//    public void testPersistAll() throws PersistException {
//        Student student = new Student();
//        student.setGroup(new Group());
//        student = (Student) factory.getDao(connection, Student.class).persist(student);
//        Assert.assertNotNull("Group is null.", student.getGroup());
//        Assert.assertNotNull("Group.id is null.", student.getGroup().getId());
//    }
//
//    @Test
//    public void testUpdate() throws PersistException {
//        Student student = (Student) factory.getDao(connection, Student.class).create();
//        student.setGroup(new Group());
//        factory.getDao(connection, Student.class).update(student);
//        Assert.assertNotNull("Group is null.", student.getGroup());
//        Assert.assertNotNull("Group.id is null.", student.getGroup().getId());
//    }
//    @Test
//    public void testUpdateAll() throws PersistException {
//        Student student = (Student) factory.getDao(connection, Student.class).create();
//        Group group = (Group) factory.getDao(connection, Group.class).create();
//        group.setNumber(1234);
//        student.setGroup(group);
//        factory.getDao(connection, Student.class).update(student);
//        Assert.assertNotNull("Group is null.", student.getGroup());
//        Assert.assertEquals("Wrong group number.", 1234, student.getGroup().getNumber());
//    }
//
//    @Test
//    public void testRead() throws PersistException {
//        Student student = (Student) factory.getDao(connection, Student.class).create();
//        student.setGroup(new Group());
//        factory.getDao(connection, Student.class).update(student);
//
//        student = (Student) factory.getDao(connection, Student.class).getByPK(student.getId());
//        Assert.assertNotNull("Student is null.", student);
//        Assert.assertNotNull("Group is null.", student.getGroup());
//    }
//
//    @Test
//    public void testDelete() throws PersistException {
//        Student student = (Student) factory.getDao(connection, Student.class).create();
//        student.setGroup(new Group());
//        factory.getDao(connection, Student.class).update(student);
//
//        Group group = student.getGroup();
//
//        factory.getDao(connection, Student.class).delete(student);
//        group = (Group) factory.getDao(connection, Group.class).getByPK(group.getId());
//        Assert.assertNotNull("Group not found.", group);
//    }
//}