package domain.patient;

import dao.Identified;
import domain.Entity;

import java.util.List;

public class Patient extends Entity implements Identified<Integer> {
    private String firstName;
    private String lastName;
    private Integer ward;
    private List<DiagnosisToPatient> history;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Integer getWard() {
        return ward;
    }

    public void setWard(final Integer ward) {
        this.ward = ward;
    }

    public List<DiagnosisToPatient> getHistory() {
        return history;
    }

    public void setHistory(final List<DiagnosisToPatient> history) {
        this.history = history;
    }
}
