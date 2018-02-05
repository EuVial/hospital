package domain.patient;

import dao.Identified;
import domain.Entity;
import domain.user.User;

import java.util.Date;
import java.util.List;

public class DiagnosisToPatient extends Entity implements Identified<Integer> {
    private Patient patient;
    private Diagnosis diagnosis;
    private User doctor;
    private Date consultationDate;
    private List<Treatment> history;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(final Patient patient) {
        this.patient = patient;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(final Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(final User doctor) {
        this.doctor = doctor;
    }

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(final Date consultationDate) {
        this.consultationDate = consultationDate;
    }

    public List<Treatment> getHistory() {
        return history;
    }

    public void setHistory(final List<Treatment> history) {
        this.history = history;
    }
}
