package dao.mysql.patient;

import dao.PersistException;
import dao.mysql.AbstractJDBCDao;
import domain.patient.Diagnosis;
import domain.patient.DiagnosisToPatient;
import domain.patient.Patient;
import domain.user.User;
import domain.user.UserRole;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MySqlDiagnosisToPatientDao extends AbstractJDBCDao<Integer, DiagnosisToPatient> {

    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(MySqlDiagnosisToPatientDao.class));

    private class PersistDiagnosisToPatient extends DiagnosisToPatient {
        public void setId(final int id) {
            super.setId(id);
        }
    }

    public MySqlDiagnosisToPatientDao(final Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, patient_id, diagnosis_id, doctor_id, consultation_date FROM hospital.patient_diagnosis";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO hospital.patient_diagnosis (patient_id, diagnosis_id, doctor_id, consultation_date)\n"
                + "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE hospital.patient_diagnosis\n"
                + "SET patient_id = ?, diagnosis_id = ?, doctor_id = ?, consultation_date = ?\n"
                + "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM hospital.patient_diagnosis WHERE id = ?;";
    }

    @Override
    public String getInitiatesQuery() {
        return "SELECT COUNT(*) AS 'count' FROM hospital.treatment\n"
                + "WHERE patient_diagnosis_id = ? LIMIT 1;";
    }

    @Override
    protected List<DiagnosisToPatient> parseResultSet(final ResultSet resultSet) throws PersistException {
        List<DiagnosisToPatient> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                PersistDiagnosisToPatient diagnosisToPatient = new PersistDiagnosisToPatient();
                diagnosisToPatient.setId(resultSet.getInt("id"));
                diagnosisToPatient.setPatient(new Patient());
                diagnosisToPatient.getPatient().setId(resultSet.getInt("patient_id"));
                diagnosisToPatient.setDiagnosis(new Diagnosis());
                diagnosisToPatient.getDiagnosis().setId(resultSet.getInt("diagnosis_id"));
                diagnosisToPatient.setDoctor(new User());
                diagnosisToPatient.getDoctor().setId(resultSet.getInt("doctor_id"));
                diagnosisToPatient.setConsultationDate(new java.util.Date(
                        resultSet.getTimestamp("consultation_date").getTime()));
                result.add(diagnosisToPatient);
            }
        } catch (Exception e) {
            LOGGER.warn("Can't parse resultSet");
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(final PreparedStatement statement, final DiagnosisToPatient object)
            throws PersistException {
        try {
            statement.setInt(1, object.getPatient().getId());
            statement.setInt(2, object.getDiagnosis().getId());
            statement.setInt(3, object.getDoctor().getId());
            statement.setTimestamp(4, new Timestamp(object.getConsultationDate().getTime()));
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for insert");
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(final PreparedStatement statement, final DiagnosisToPatient object)
            throws PersistException {
        try {
            statement.setInt(1, object.getPatient().getId());
            statement.setInt(2, object.getDiagnosis().getId());
            statement.setInt(3, object.getDoctor().getId());
            statement.setTimestamp(4, new Timestamp(object.getConsultationDate().getTime()));
            statement.setInt(5, object.getId());
        } catch (SQLException e) {
            LOGGER.warn("Can't prepare statement for update");
            throw new PersistException(e);
        }
    }

    public List<DiagnosisToPatient> readHistory(final Integer patientId) throws PersistException {
        String sql =
                "SELECT patient_diagnosis.id, diagnosis.title, user.id, user.first_name, "
                        + "user.last_name, user.role_id, patient_diagnosis.consultation_date\n"
                + "FROM hospital.patient_diagnosis\n"
                + "  JOIN hospital.user ON (patient_diagnosis.doctor_id = user.id)\n"
                + "  JOIN hospital.diagnosis ON (patient_diagnosis.diagnosis_id = diagnosis.id)\n"
                + "WHERE patient_diagnosis.patient_id = ?;";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<DiagnosisToPatient> diagnosisToPatients = new ArrayList<>();
                while (resultSet.next()) {
                    DiagnosisToPatient diagnosisToPatient = new DiagnosisToPatient();
                    diagnosisToPatient.setId(resultSet.getInt("patient_diagnosis.id"));
                    diagnosisToPatient.setDiagnosis(new Diagnosis());
                    diagnosisToPatient.getDiagnosis().setTitle(resultSet.getString("diagnosis.title"));
                    diagnosisToPatient.setDoctor(new User());
                    diagnosisToPatient.getDoctor().setId(resultSet.getInt("user.id"));
                    diagnosisToPatient.getDoctor().setFirstName(resultSet.getString("user.first_name"));
                    diagnosisToPatient.getDoctor().setLastName(resultSet.getString("user.last_name"));
                    diagnosisToPatient.getDoctor().setRole(
                            UserRole.values()[resultSet.getInt("user.role_id")]);
                    diagnosisToPatient.setConsultationDate(new java.util.Date(resultSet.getTimestamp(
                            "patient_diagnosis.consultation_date").getTime()));
                    diagnosisToPatients.add(diagnosisToPatient);
                }
                return diagnosisToPatients;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read DiagnosisToPatient list from patient");
            throw new PersistException(e);
        }
    }

    public DiagnosisToPatient readInfo(Integer diagnosisToPatientId) throws PersistException {
        String sql =
                "SELECT diagnosis.title, user.id, user.first_name, user.last_name, user.role_id, "
                        + "patient_diagnosis.consultation_date,"
                        + "patient.id, patient.first_name, patient.last_name, patient.ward\n"
                        + "FROM hospital.patient_diagnosis\n"
                        + "  JOIN hospital.user ON (patient_diagnosis.doctor_id = user.id)\n"
                        + "  JOIN hospital.diagnosis ON (patient_diagnosis.diagnosis_id = diagnosis.id)\n"
                        + "  JOIN hospital.patient ON (patient_diagnosis.patient_id = patient.id)\n"
                        + "WHERE patient_diagnosis.id = ?;";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, diagnosisToPatientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                DiagnosisToPatient diagnosisToPatient = new DiagnosisToPatient();
                diagnosisToPatient.setId(diagnosisToPatientId);
                while (resultSet.next()) {
                    diagnosisToPatient.setDiagnosis(new Diagnosis());
                    diagnosisToPatient.getDiagnosis().setTitle(resultSet.getString("diagnosis.title"));
                    diagnosisToPatient.setDoctor(new User());
                    diagnosisToPatient.getDoctor().setId(resultSet.getInt("user.id"));
                    diagnosisToPatient.getDoctor().setFirstName(resultSet.getString("user.first_name"));
                    diagnosisToPatient.getDoctor().setLastName(resultSet.getString("user.last_name"));
                    diagnosisToPatient.getDoctor().setRole(UserRole.values()[resultSet.getInt("user.role_id")]);
                    diagnosisToPatient.setConsultationDate(new java.util.Date(resultSet.getTimestamp("patient_diagnosis.consultation_date").getTime()));
                    // Data about the patient is necessary for the correct operation of the "caps" of the jsp-page and the "back"
                    diagnosisToPatient.setPatient(new Patient());
                    diagnosisToPatient.getPatient().setId(resultSet.getInt("patient.id"));
                    diagnosisToPatient.getPatient().setFirstName(resultSet.getString("patient.first_name"));
                    diagnosisToPatient.getPatient().setLastName(resultSet.getString("patient.last_name"));
                    diagnosisToPatient.getPatient().setWard(resultSet.getInt("ward"));
                }
                return diagnosisToPatient;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read info about extended diagnosis by ID");
            throw new PersistException(e);
        }
    }

    public String readDiagnosisTitle(Integer diagnosisToPatientId) throws PersistException {
        String sql =
                "SELECT diagnosis.title "
                        + "FROM hospital.patient_diagnosis\n"
                        + "  JOIN hospital.diagnosis ON (patient_diagnosis.diagnosis_id = diagnosis.id)\n"
                        + "WHERE patient_diagnosis.id = ?;";
        String diagnosisTitle = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, diagnosisToPatientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                DiagnosisToPatient diagnosisToPatient = new DiagnosisToPatient();
                diagnosisToPatient.setId(diagnosisToPatientId);
                while (resultSet.next()) {
                    diagnosisTitle = resultSet.getString("diagnosis.title");
                }
                return diagnosisTitle;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read title from diagnosis by diagnosisToPatientId");
            throw new PersistException(e);
        }
    }

    public Integer getDiagnosisToPatientId(final String diagnosisTitle, final Integer patientId)
            throws PersistException {
        String sql =
                "SELECT patient_diagnosis.id FROM hospital.patient_diagnosis\n"
                        + "  JOIN hospital.diagnosis ON (hospital.diagnosis.id = hospital.patient_diagnosis.diagnosis_id)\n"
                        + "  JOIN hospital.patient ON (hospital.patient.id = hospital.patient_diagnosis.patient_id)\n"
                        + "WHERE diagnosis.title = ? AND patient.id = ?;";
        Integer diagnosisToPatientId = null;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, diagnosisTitle);
            statement.setInt(2, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    diagnosisToPatientId = resultSet.getInt("patient_diagnosis.id");
                }
                return diagnosisToPatientId;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read patientDiagnosisId by patientID and diagnosis title");
            throw new PersistException(e);
        }
    }
}

