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

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }

    public List<Treatment> getHistory() {
        return history;
    }

    public void setHistory(List<Treatment> history) {
        this.history = history;
    }
}
