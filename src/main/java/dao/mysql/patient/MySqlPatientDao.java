package dao.mysql.patient;

import dao.PersistException;
import domain.patient.Patient;
import dao.mysql.AbstractJDBCDao;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MySqlPatientDao extends AbstractJDBCDao<Integer, Patient> {

    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(MySqlPatientDao.class));

    private class PersistPatient extends Patient {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlPatientDao(Connection connection) {
        super(connection);
    }

//    @Override
//    public Patient create() throws PersistException {
//        Patient patient = new Patient();
//        return persist(patient);
//    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, first_name, last_name, ward FROM hospital.patient";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO hospital.patient (first_name, last_name, ward)\n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE hospital.patient\n" +
                "SET first_name = ?, last_name = ?, ward = ?\nWHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM hospital.patient WHERE id = ?;";
    }

    @Override
    public String getInitiatesQuery() {
        return "SELECT COUNT(*) AS 'count' FROM hospital.patient_diagnosis WHERE patient_id = ? LIMIT 1;";
    }

    @Override
    protected List<Patient> parseResultSet(ResultSet rs) throws PersistException {
        List<Patient> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistPatient patient = new PersistPatient();
                patient.setId(rs.getInt("id"));
                patient.setFirstName(rs.getString("first_name"));
                patient.setLastName(rs.getString("last_name"));
                patient.setWard(rs.getInt("ward"));
                result.add(patient);
            }
        } catch (Exception e) {
            LOGGER.warn("Can't parse resultSet");
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Patient object) throws PersistException {
        try {
            statement.setString(1, object.getFirstName());
            statement.setString(2, object.getLastName());
            statement.setInt(3, object.getWard());
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for insert");
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Patient object) throws PersistException {
        try {
            statement.setString(1, object.getFirstName());
            statement.setString(2, object.getLastName());
            Object ward = object.getWard();
            if (ward != null) {
                statement.setInt(3, (Integer) ward);
            } else {
                statement.setNull(3, Types.INTEGER);
            }
            statement.setInt(4, object.getId());
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for update");
            throw new PersistException(e);
        }
    }
}
