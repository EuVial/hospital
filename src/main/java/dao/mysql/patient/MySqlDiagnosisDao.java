package dao.mysql.patient;

import dao.PersistException;
import dao.mysql.AbstractJDBCDao;
import domain.patient.Diagnosis;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MySqlDiagnosisDao extends AbstractJDBCDao<Integer, Diagnosis> {

    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(MySqlDiagnosisDao.class));

    private class PersistDiagnosis extends Diagnosis {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlDiagnosisDao(Connection connection) {
        super(connection);
    }

//    @Override
//    public Patient create() throws PersistException {
//        Patient patient = new Patient();
//        return persist(patient);
//    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, title FROM hospital.diagnosis";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO hospital.diagnosis (title)\n" +
                "VALUES (?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE hospital.diagnosis\n" +
                "SET title = ?\n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM hospital.diagnosis WHERE id = ?;";
    }

    @Override
    public String getInitiatesQuery() {
        return "SELECT COUNT(*) AS 'count' FROM hospital.patient_diagnosis WHERE diagnosis_id = ? LIMIT 1;";
    }

    @Override
    protected List<Diagnosis> parseResultSet(ResultSet rs) throws PersistException {
        List<Diagnosis> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistDiagnosis diagnosis = new PersistDiagnosis();
                diagnosis.setId(rs.getInt("id"));
                diagnosis.setTitle(rs.getString("title"));
                result.add(diagnosis);
            }
        } catch (Exception e) {
            LOGGER.warn("Can't parse resultSet");
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Diagnosis object) throws PersistException {
        try {
            statement.setString(1, object.getTitle());
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for insert");
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Diagnosis object) throws PersistException {
        try {
            statement.setString(1, object.getTitle());
            statement.setInt(2, object.getId());
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for update");
            throw new PersistException(e);
        }
    }

    public Integer getIdByTitle(String title) throws PersistException {
        String sql = "SELECT id FROM hospital.diagnosis WHERE title = ?;";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        // TODO: try-with-resources
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, title);
            resultSet = statement.executeQuery();
            Integer diagnosisId = null;
            while(resultSet.next()) {
                diagnosisId = resultSet.getInt("id");
            }
            return diagnosisId;
        } catch(SQLException e) {
            throw new PersistException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }
}