//package com.epam.mysql;
//
//import PersistException;
//import User;
//import UserRole;
//import UserSpecialization;
//import AbstractJDBCDao;
//import org.apache.log4j.Logger;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.LinkedList;
//import java.util.List;
//
//public class MySqlUserDao extends AbstractJDBCDao<Integer, User> {
//
//    private final static Logger LOGGER =
//            Logger.getLogger(String.valueOf(MySqlUserDao.class));
//
//    private class PersistUser extends User {
//        public void setId(int id) {
//            super.setId(id);
//        }
//    }
//
//    public MySqlUserDao(Connection connection) {
//        super(connection);
//    }
//
//    @Override
//    public User create() throws PersistException {
//        User user = new User();
//        return persist(user);
//    }
//
//    @Override
//    public void update(User user) {
//
//    }
//
//    @Override
//    public void delete(User user) {
//
//    }
//
//    @Override
//    public String getSelectQuery() {
//        return "SELECT id, login, password, first_name, last_name, role_id " +
//                "FROM hospital.user";
//    }
//
//    @Override
//    public String getCreateQuery() {
//        return "INSERT INTO hospital.user (id, login, password, first_name, " +
//                "last_name, role_id)";
//    }
//
//    @Override
//    public String getUpdateQuery() {
//        return "UPDATE hospital.user \n" +
//                "SET login = ?, password = ?, first_name = ?, last_name = ?, " +
//                "role_id = ?\n" +
//                "WHERE id = ?";
//    }
//
//    @Override
//    public String getDeleteQuery() {
//        return "DELETE FROM hospital.user WHERE id = ?";
//    }
//
//    @Override
//    protected List<User> parseResultSet(ResultSet resultSet) throws PersistException {
//        List<User> result = new LinkedList<>();
//        try {
//            while (resultSet.next()) {
//                PersistUser user = new PersistUser();
//                user.setId(resultSet.getInt("id"));
//                user.setLogin(resultSet.getString("login"));
//                user.setPassword(resultSet.getString("password"));
//                user.setFirstName(resultSet.getString("first_name"));
//                user.setLastName(resultSet.getString("last_name"));
//                user.setRole(UserRole.values()[resultSet.getInt("role_id") - 1]);
//                user.setspecialization(new UserSpecialization());
//                user.getspecialization().setId(resultSet.getInt("specialization_id"));
//                result.add(user);
//            }
//        } catch (Exception e) {
//            LOGGER.warn("Can't parse resultSet");
//            throw new PersistException(e);
//        }
//        return result;
//    }
//
//    @Override
//    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
//        try {
//            statement.setString(1, object.getLogin());
//            statement.setString(2, object.getPassword());
//            statement.setString(3, object.getFirstName());
//            statement.setString(4, object.getLastName());
//            statement.setInt(5, object.getRole().ordinal());
//            statement.setInt(6, object.getspecialization().getId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            LOGGER.warn("Can't prepare statement for insert");
//            throw new PersistException(e);
//        }
//    }
//
//    @Override
//    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
//        try {
//            statement.setString(1, object.getLogin());
//            statement.setString(2, object.getPassword());
//            statement.setString(3, object.getFirstName());
//            statement.setString(4, object.getLastName());
//            statement.setInt(5, object.getRole().ordinal());
//            statement.setInt(6, object.getspecialization().getId());
//            LOGGER.warn("Can't prepare statement for update");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
