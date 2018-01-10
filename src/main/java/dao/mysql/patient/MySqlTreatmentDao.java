package dao.mysql.patient;

import dao.PersistException;
import dao.mysql.AbstractJDBCDao;
import domain.patient.DiagnosisToPatient;
import domain.patient.Treatment;
import domain.patient.TreatmentType;
import domain.user.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MySqlTreatmentDao extends AbstractJDBCDao<Integer, Treatment> {

    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(MySqlTreatmentDao.class));

    private class PersistTreatment extends Treatment {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlTreatmentDao(Connection connection) {
        super(connection);
    }

//    @Override
//    public Patient create() throws PersistException {
//        Patient patient = new Patient();
//        return persist(patient);
//    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, title, patient_diagnosis_id, type_id, performer_id, done FROM hospital.treatment";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO hospital.treatment (title, patient_diagnosis_id, type_id, performer_id, done)\n" +
                "VALUES (?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE hospital.treatment\n" +
                "SET title = ?, patient_diagnosis_id = ?, type_id = ?, performer_id = ?, done = ?\n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM hospital.treatment WHERE id = ?;";
    }

    @Override
    public String getInitiatesQuery() {
        //TODO: Set "always can delete"
        return "1";
//        return "SELECT COUNT(*) AS 'count' FROM hospital.patient_diagnosis WHERE patient_id = ? LIMIT 1;";
    }

    @Override
    protected List<Treatment> parseResultSet(ResultSet rs) throws PersistException {
        List<Treatment> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistTreatment treatment = new PersistTreatment();
                treatment.setId(rs.getInt("id"));
                treatment.setTitle(rs.getString("title"));
                treatment.setDiagnosisToPatient(new DiagnosisToPatient());
                treatment.getDiagnosisToPatient().setId(rs.getInt("patient_diagnosis_id"));
                treatment.setType(TreatmentType.values()[rs.getInt("type_id")]);
                treatment.setPerformer(new User());
                treatment.getPerformer().setId(rs.getInt("performer_id"));
//                TODO: maybe set "treatment.done" boolean type
                treatment.setDone(Boolean.valueOf(String.valueOf(rs.getInt("done"))));
                result.add(treatment);
            }
        } catch (Exception e) {
            LOGGER.warn("Can't parse resultSet");
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Treatment object) throws PersistException {
        try {
            statement.setString(1, object.getTitle());
            statement.setInt(2, object.getDiagnosisToPatient().getId());
            statement.setInt(3, object.getType().ordinal());
            statement.setInt(4, object.getPerformer().getId());
            statement.setInt(5, object.isDone() ? 1 : 0);
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for insert");
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Treatment object) throws PersistException {
        try {
            statement.setString(1, object.getTitle());
            statement.setInt(2, object.getDiagnosisToPatient().getId());
            statement.setInt(3, object.getType().ordinal());
            statement.setInt(4, object.getPerformer().getId());
            statement.setInt(5, object.isDone() ? 1 : 0);
            statement.setInt(6, object.getId());
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for update");
            throw new PersistException(e);
        }
    }

    public List<Treatment> readHistoryOfDiagnosisToPatient(Integer diagnosisToPatientId) throws PersistException {
        String sql = "SELECT id, title, type_id, performer_id, done FROM hospital.treatment WHERE patient_diagnosis_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        // TODO: try-with-resources
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, diagnosisToPatientId);
            resultSet = statement.executeQuery();
            List<Treatment> treatments = new ArrayList<>();
            while(resultSet.next()) {
                Treatment treatment = new Treatment();
                treatment.setId(resultSet.getInt("id"));
                treatment.setTitle(resultSet.getString("title"));
//                diagnosisToPatientId = resultSet.getInt("patient_diagnosis_id");
//                if (!resultSet.wasNull()) {
//                    treatment.setDiagnosisToPatient(new DiagnosisToPatient());
//                    treatment.getDiagnosisToPatient().setId(resultSet.getInt("patient_diagnosis_id"));
//                }
                treatment.setDiagnosisToPatient(new DiagnosisToPatient());
                treatment.getDiagnosisToPatient().setId(diagnosisToPatientId);
                treatment.setType(TreatmentType.values()[resultSet.getInt("type_id")]);
                treatment.setPerformer(new User());
                treatment.getPerformer().setId(resultSet.getInt("performer_id"));
                treatment.setDone(Boolean.valueOf(String.valueOf(resultSet.getInt("done"))));

                treatments.add(treatment);
            }
            return treatments;
        } catch(SQLException e) {
            throw new PersistException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }
}
