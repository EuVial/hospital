package dao.mysql.patient;

import dao.PersistException;
import dao.mysql.AbstractJDBCDao;
import domain.patient.*;
import domain.user.User;
import domain.user.UserRole;
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
                treatment.setDone(rs.getInt("done") == 1);
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
            statement.setInt(5, object.getIsDone() ? 1 : 0);
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
            statement.setInt(5, object.getIsDone() ? 1 : 0);
            statement.setInt(6, object.getId());
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for update");
            throw new PersistException(e);
        }
    }

    public List<Treatment> readTreatmentsFromDiagnosisToPatient(Integer diagnosisToPatientId) throws PersistException {
        String sql = "SELECT id, title, type_id, performer_id, done FROM hospital.treatment WHERE patient_diagnosis_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, diagnosisToPatientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Treatment> treatments = new ArrayList<>();
                while (resultSet.next()) {
                    Treatment treatment = new Treatment();
                    treatment.setId(resultSet.getInt("id"));
                    treatment.setTitle(resultSet.getString("title"));
                    treatment.setDiagnosisToPatient(new DiagnosisToPatient());
                    treatment.getDiagnosisToPatient().setId(diagnosisToPatientId);
                    treatment.setType(TreatmentType.values()[resultSet.getInt("type_id")]);
                    treatment.setPerformer(new User());
                    treatment.getPerformer().setId(resultSet.getInt("performer_id"));
                    treatment.setDone(resultSet.getInt("done") == 1);
                    treatments.add(treatment);
                }
                return treatments;
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    public Treatment readInfo(Integer treatmentId) throws PersistException {
        String sql =
                "SELECT treatment.title, treatment.type_id, treatment.done, patient_diagnosis.doctor_id, " +
//                        "user.first_name, user.last_name, user.role_id, " +
                        "diagnosis.title, patient.id, patient.first_name, patient.last_name, patient.ward\n" +
                        "FROM hospital.treatment\n" +
                        "  JOIN hospital.patient_diagnosis ON (treatment.patient_diagnosis_id = patient_diagnosis.id)\n" +
//                        "  JOIN hospital.user ON (treatment.performer_id = user.id)\n" +
                        "  JOIN hospital.diagnosis ON (patient_diagnosis.diagnosis_id = diagnosis.id)\n" +
                        "  JOIN hospital.patient ON (patient_diagnosis.patient_id = patient.id)\n" +
                        "WHERE treatment.id = ?;";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, treatmentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                Treatment treatment = new Treatment();
                treatment.setId(treatmentId);
                while (resultSet.next()) {
                    treatment.setTitle(resultSet.getString("treatment.title"));
                    treatment.setType(TreatmentType.values()[resultSet.getInt("treatment.type_id")]);
                    treatment.setDone(resultSet.getInt("treatment.done") == 1);
                    treatment.setDiagnosisToPatient(new DiagnosisToPatient());
                    treatment.getDiagnosisToPatient().setDoctor(new User());
                    treatment.getDiagnosisToPatient().getDoctor().setId(resultSet.getInt("patient_diagnosis.doctor_id"));
                    treatment.getDiagnosisToPatient().setDiagnosis(new Diagnosis());
                    treatment.getDiagnosisToPatient().getDiagnosis().setTitle(resultSet.getString("diagnosis.title"));
                    // Data about the patient is necessary for the correct operation of the "caps" of the jsp-page and the "back"
                    treatment.setPatient(new Patient());
                    treatment.getPatient().setId(resultSet.getInt("patient.id"));
                    treatment.getPatient().setFirstName(resultSet.getString("patient.first_name"));
                    treatment.getPatient().setLastName(resultSet.getString("patient.last_name"));
                    treatment.getPatient().setWard(resultSet.getInt("ward"));
                }
                return treatment;
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    public Treatment readInfoIfDone(Integer treatmentId) throws PersistException {
        String sql =
                "SELECT treatment.title, treatment.type_id, treatment.done," +
                        "user.first_name, user.last_name, user.role_id, patient_diagnosis.doctor_id, " +
                        "diagnosis.title, patient.id, patient.first_name, patient.last_name, patient.ward\n" +
                        "FROM hospital.treatment\n" +
                        "  JOIN hospital.patient_diagnosis ON (treatment.patient_diagnosis_id = patient_diagnosis.id)\n" +
                        "  JOIN hospital.user ON (treatment.performer_id = user.id)\n" +
                        "  JOIN hospital.diagnosis ON (patient_diagnosis.diagnosis_id = diagnosis.id)\n" +
                        "  JOIN hospital.patient ON (patient_diagnosis.patient_id = patient.id)\n" +
                        "WHERE treatment.id = ?;";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, treatmentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                Treatment treatment = new Treatment();
                treatment.setId(treatmentId);
                while (resultSet.next()) {
                    treatment.setTitle(resultSet.getString("treatment.title"));
                    treatment.setType(TreatmentType.values()[resultSet.getInt("treatment.type_id")]);
                    treatment.setDone(resultSet.getInt("treatment.done") == 1);
                    treatment.setPerformer(new User());
                    treatment.getPerformer().setFirstName(resultSet.getString("user.first_name"));
                    treatment.getPerformer().setLastName(resultSet.getString("user.last_name"));
                    treatment.getPerformer().setRole(UserRole.values()[resultSet.getInt("user.role_id")]);
                    treatment.setDiagnosisToPatient(new DiagnosisToPatient());
                    treatment.getDiagnosisToPatient().setDoctor(new User());
                    treatment.getDiagnosisToPatient().getDoctor().setId(resultSet.getInt("patient_diagnosis.doctor_id"));
                    treatment.getDiagnosisToPatient().setDiagnosis(new Diagnosis());
                    treatment.getDiagnosisToPatient().getDiagnosis().setTitle(resultSet.getString("diagnosis.title"));
                    // Data about the patient is necessary for the correct operation of the "caps" of the jsp-page and the "back"
                    treatment.setPatient(new Patient());
                    treatment.getPatient().setId(resultSet.getInt("patient.id"));
                    treatment.getPatient().setFirstName(resultSet.getString("patient.first_name"));
                    treatment.getPatient().setLastName(resultSet.getString("patient.last_name"));
                    treatment.getPatient().setWard(resultSet.getInt("ward"));
                }
                return treatment;
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    public void done(Treatment treatment) throws PersistException {
        String sql = "UPDATE hospital.treatment SET done = ?, performer_id = ? WHERE id = ?;";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, 1);
            statement.setInt(2, treatment.getPerformer().getId());
            statement.setInt(3, treatment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for update");
            throw new PersistException(e);
        }
    }
}
