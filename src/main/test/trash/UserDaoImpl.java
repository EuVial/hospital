//package com.epam.dao.mysql.user;
//
//import PersistException;
//import User;
//import UserRole;
//import UserSpecialization;
//import com.epam.dao.mysql.BaseDao;
//import MySqlUserDao;
//import com.epam.newDao.user.UserDao;
//import org.apache.log4j.Logger;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoImpl extends BaseDao implements UserDao {
//
//    private final static Logger LOGGER =
//            Logger.getLogger(String.valueOf(MySqlUserDao.class));
//
//    @Override
//    public Integer create(User user) throws PersistException {
//        String sqlScript = "INSERT INTO 'user' ('login', 'password', " +
//                "'first_name', 'last_name', 'role_id', 'specialization_id') " +
//                "VALUES (?, ?, ?, ?, ?, ?)";
//        Connection connection = getConnection();
//        try (PreparedStatement statement = connection.prepareStatement(sqlScript,
//                PreparedStatement.RETURN_GENERATED_KEYS);
//             ResultSet resultSet = statement.getGeneratedKeys()) {
//            statement.setString(1, user.getLogin());
//            statement.setString(2, user.getPassword());
//            statement.setString(3, user.getFirstName());
//            statement.setString(4, user.getLastName());
//            statement.setInt(5, user.getRole().ordinal());
//            statement.setInt(6, user.getspecialization().getId());
//            statement.executeUpdate();
//            resultSet.next();
//            return resultSet.getInt(1);
//        } catch (SQLException e) {
//            LOGGER.warn("Can't parse resultSet");
//            throw new PersistException(e);
//        }
//    }
//
//    @Override
//    public User read(Integer id) throws PersistException {
//        String sqlScript = "SELECT 'login', 'password', 'first_name', " +
//                "'last_name', 'role_id', 'specialization_id' " +
//                "FROM 'user' WHERE 'id' = ?";
//        Connection connection = getConnection();
//        try (PreparedStatement statement = connection.prepareStatement(sqlScript);
//                ResultSet resultSet = statement.executeQuery()) {
//            statement.setInt(1, id);
//            User user = null;
//            if(resultSet.next()) {
//                user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setLogin(resultSet.getString("login"));
//                user.setPassword(resultSet.getString("password"));
//                user.setFirstName(resultSet.getString("first_name"));
//                user.setLastName(resultSet.getString("last_name"));
//                user.setRole(UserRole.values()[resultSet.getInt("role_id") - 1]);
//                user.setspecialization(new UserSpecialization());
//                user.getspecialization().setId(resultSet.getInt("specialization_id"));
//            }
//            return user;
//        } catch(SQLException e) {
//            LOGGER.warn("Can't parse resultSet");
//            throw new PersistException(e);
//        }
//    }
//
//    @Override
//    public void update(User user) throws PersistException {
//        String sqlScript = "UPDATE 'user' SET 'login' = ?, 'password' = ?, " +
//                "'first_name' = ? , 'last_name' = ?, 'role_id' = ?, " +
//                "'specialization_id' = ? WHERE 'id' = ?";
//        Connection connection = getConnection();
//        try (PreparedStatement statement =
//                     connection.prepareStatement(sqlScript)) {
//            statement.setString(1, user.getLogin());
//            statement.setString(2, user.getPassword());
//            statement.setString(3, user.getFirstName());
//            statement.setString(4, user.getLastName());
//            statement.setInt(5, user.getRole().ordinal());
//            statement.setInt(6, user.getspecialization().getId());
//            statement.setInt(7, user.getId());
//            statement.executeUpdate();
//        } catch(SQLException e) {
//            throw new PersistException(e);
//        }
//    }
//
//    @Override
//    public void delete(Integer id) throws PersistException {
//        String sqlScript = "DELETE FROM 'user' WHERE 'id' = ?";
//        Connection connection = getConnection();
//        try (PreparedStatement statement =
//                     connection.prepareStatement(sqlScript)) {
//            statement.setInt(1, id);
//            statement.executeUpdate();
//        } catch(SQLException e) {
//            throw new PersistException(e);
//        }
//    }
//
//    @Override
//    public List<User> read() throws PersistException {
//        String sqlScript = "SELECT 'id', 'name', 'password', 'first_name', 'last_name', 'role_id', 'specialization_id' FROM 'user'";
//        Connection connection = getConnection();
//        try (PreparedStatement statement = connection.prepareStatement(sqlScript);
//            ResultSet resultSet = statement.executeQuery()) {
//            List<User> users = new ArrayList<>();
//            User user = null;
//            while(resultSet.next()) {
//                user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setLogin(resultSet.getString("login"));
//                user.setPassword(resultSet.getString("password"));
//                user.setFirstName(resultSet.getString("first_name"));
//                user.setLastName(resultSet.getString("last_name"));
//                user.setRole(UserRole.values()[resultSet.getInt("role_id") - 1]);
//                user.setspecialization(new UserSpecialization());
//                user.getspecialization().setId(resultSet.getInt("specialization_id"));
//                users.add(user);
//            }
//            return users;
//        } catch(SQLException e) {
//            throw new PersistException(e);
//        }
//    }
//}
