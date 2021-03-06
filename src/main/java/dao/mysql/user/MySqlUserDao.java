package dao.mysql.user;

import domain.user.User;
import domain.user.UserRole;
import dao.mysql.AbstractJDBCDao;
import dao.PersistException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MySqlUserDao extends AbstractJDBCDao<Integer, User> {

    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(MySqlUserDao.class));

    public User readByLoginAndPassword(final String login, final String password)
            throws PersistException {
        String sql = "SELECT id, role_id FROM hospital.user WHERE login = ? AND password = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                User user = null;
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(login);
                    user.setPassword(password);
                    user.setRole(UserRole.values()[resultSet.getInt("role_id")]);
                }
                return user;
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    private class PersistUser extends User {
        public void setId(final int id) {
            super.setId(id);
        }
    }

    public MySqlUserDao(final Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, login, password, first_name, last_name, role_id "
                + "FROM hospital.user";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO hospital.user (login, password, first_name, "
                + "last_name, role_id)\n"
                + "VALUES (?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE hospital.user\n"
                + "SET login = ?, password = ?, first_name = ?, last_name = ?, "
                + "role_id = ?\n"
                + "WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM hospital.user WHERE id = ?";
    }

    @Override
    public String getInitiatesQuery() {
        return "SELECT COUNT(*) AS 'count' FROM hospital.treatment WHERE performer_id = ? LIMIT 1;";
    }

    @Override
    protected List<User> parseResultSet(final ResultSet rs) throws PersistException {
        List<User> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistUser user = new PersistUser();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setRole(UserRole.values()[rs.getInt("role_id")]);
                result.add(user);
            }
        } catch (Exception e) {
            LOGGER.warn("Can't parse resultSet");
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(final PreparedStatement statement, final User object)
            throws PersistException {
        try {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getFirstName());
            statement.setString(4, object.getLastName());
            statement.setInt(5, object.getRole().ordinal());
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for insert");
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(final PreparedStatement statement, final User object)
            throws PersistException {
        try {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getFirstName());
            statement.setString(4, object.getLastName());
            statement.setInt(5, object.getRole().ordinal());
            statement.setInt(6, object.getId());
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for update");
            throw new PersistException(e);
        }
    }

    public User readByLogin(final String login) throws PersistException {
        String sql = "SELECT id, password, first_name, last_name, role_id FROM hospital.user WHERE login = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                User user = null;
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(login);
                    user.setPassword(resultSet.getString("password"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setRole(UserRole.values()[resultSet.getInt("role_id")]);
                }
                return user;
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
