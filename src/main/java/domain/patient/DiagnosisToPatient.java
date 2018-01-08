package domain.patient;

import domain.Entity;

import java.util.Date;

public class DiagnosisToPatient extends Entity {
    private Patient patient;
    private Diagnosis diagnosis;
    //TODO: consultationDate might be deleted?
    private Date consultationDate;

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

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }
}
