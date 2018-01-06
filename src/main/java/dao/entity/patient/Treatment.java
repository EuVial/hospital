package dao.entity.patient;

import dao.Identified;
import dao.entity.Entity;
import dao.entity.user.User;

public class Treatment extends Entity implements Identified<Integer> {
    private String title;
    private TreatmentType type;
    private DiagnosisToPatient diagnosisToPatient;
    private User performer;
    private boolean isDone;

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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
