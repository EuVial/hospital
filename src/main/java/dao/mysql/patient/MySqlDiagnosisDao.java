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
        public void setId(final int id) {
            super.setId(id);
        }
    }

    public MySqlDiagnosisDao(final Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, title FROM hospital.diagnosis";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO hospital.diagnosis (title)\n"
                + "VALUES (?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE hospital.diagnosis\n"
                + "SET title = ?\n"
                + "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM hospital.diagnosis\n"
                + "WHERE id = ?;";
    }

    @Override
    public String getInitiatesQuery() {
        return "SELECT COUNT(*) AS 'count'\n"
                + "FROM hospital.patient_diagnosis\n"
                + "WHERE diagnosis_id = ? LIMIT 1;";
    }

    @Override
    protected List<Diagnosis> parseResultSet(final ResultSet rs) throws PersistException {
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
    protected void prepareStatementForInsert(final PreparedStatement statement, final Diagnosis object)
            throws PersistException {
        try {
            statement.setString(1, object.getTitle());
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for insert");
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(final PreparedStatement statement, final Diagnosis object)
            throws PersistException {
        try {
            statement.setString(1, object.getTitle());
            statement.setInt(2, object.getId());
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for update");
            throw new PersistException(e);
        }
    }

    public Integer getIdByTitle(final String title) throws PersistException {
        String sql = "SELECT id FROM hospital.diagnosis WHERE title = ?;";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                Integer diagnosisId = null;
                while (resultSet.next()) {
                    diagnosisId = resultSet.getInt("id");
                }
                return diagnosisId;
            }
        } catch (SQLException e) {
            LOGGER.warn("Cannot get diagnosis id by title from SQL" + e);
            throw new PersistException(e);
        }
    }
}