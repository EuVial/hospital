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

    public void setTitle(String title) {
        this.title = title;
    }

    public TreatmentType getType() {
        return type;
    }

    public void setType(TreatmentType type) {
        this.type = type;
    }

    public DiagnosisToPatient getDiagnosisToPatient() {
        return diagnosisToPatient;
    }

    public void setDiagnosisToPatient(DiagnosisToPatient diagnosisToPatient) {
        this.diagnosisToPatient = diagnosisToPatient;
    }

    public User getPerformer() {
        return performer;
    }

    public void setPerformer(User performer) {
        this.performer = performer;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // this strange name of getter because jsp cannot read this value without getter which starts with "get"
//    public boolean getIsDone() {
//        return done;
//    }
    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
