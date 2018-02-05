package domain.patient;

import dao.Identified;
import domain.Entity;
import domain.user.User;

public class Treatment extends Entity implements Identified<Integer> {
    private String title;
    private TreatmentType type;
    private DiagnosisToPatient diagnosisToPatient;
    private User performer;
    private Patient patient;
    private boolean done;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public TreatmentType getType() {
        return type;
    }

    public void setType(final TreatmentType type) {
        this.type = type;
    }

    public DiagnosisToPatient getDiagnosisToPatient() {
        return diagnosisToPatient;
    }

    public void setDiagnosisToPatient(final DiagnosisToPatient diagnosisToPatient) {
        this.diagnosisToPatient = diagnosisToPatient;
    }

    public User getPerformer() {
        return performer;
    }

    public void setPerformer(final User performer) {
        this.performer = performer;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(final Patient patient) {
        this.patient = patient;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }
}
