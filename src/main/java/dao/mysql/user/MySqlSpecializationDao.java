//package dao.mysql.user;
//
//import dao.mysql.AbstractJDBCDao;
//import dao.PersistException;
//import org.apache.log4j.Logger;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.LinkedList;
//import java.util.List;
//
//public class MySqlSpecializationDao extends AbstractJDBCDao<Integer, UserSpecialization> {
//
//    private final static Logger LOGGER =
//            Logger.getLogger(String.valueOf(MySqlUserDao.class));
//
//    private class PersistSpecialization extends UserSpecialization {
//        public void setId(int id) {
//            super.setId(id);
//        }
//    }
//
//    public MySqlSpecializationDao(Connection connection) {
//        super(connection);
//    }
//
////    @Override
////    public User create() throws PersistException {
////        User user = new User();
////        return persist(user);
////    }
//
//    @Override
//    public String getSelectQuery() {
//        return "SELECT id, title FROM hospital.specialization";
//    }
//
//    @Override
//    public String getCreateQuery() {
//        return "INSERT INTO hospital.specialization (title) VALUES (?);";
//    }
//
//    @Override
//    public String getUpdateQuery() {
//        return "UPDATE hospital.specialization\nSET title = ?\nWHERE id = ?";
//    }
//
//    @Override
//    public String getDeleteQuery() {
//        return "DELETE FROM hospital.specialization WHERE id = ?";
//    }
//
//    @Override
//    protected List<UserSpecialization> parseResultSet(ResultSet rs) throws PersistException {
//        List<UserSpecialization> result = new LinkedList<>();
//        try {
//            while (rs.next()) {
//                PersistSpecialization specialization = new PersistSpecialization();
//                specialization.setId(rs.getInt("id"));
//                specialization.setTitle(rs.getString("title"));
//                result.add(specialization);
//            }
//        } catch (Exception e) {
//            LOGGER.warn("Can't parse UserSpecialization resultSet");
//            throw new PersistException(e);
//        }
//        return result;
//    }
//
//    @Override
//    protected void prepareStatementForInsert(PreparedStatement statement, UserSpecialization object) throws PersistException {
//        try {
//            statement.setString(1, object.getTitle());
//        } catch (SQLException e) {
//            LOGGER.warn("Can't prepare UserSpecialization statement for insert");
//            throw new PersistException(e);
//        }
//    }
//
//    @Override
//    protected void prepareStatementForUpdate(PreparedStatement statement, UserSpecialization object) throws PersistException {
//        try {
//            statement.setString(1, object.getTitle());
//            statement.setInt(2, object.getId());
//        } catch (SQLException e) {
//            LOGGER.warn("Can't prepare UserSpecialization statement for update");
//            throw new PersistException(e);
//        }
//    }
//}
