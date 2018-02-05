package domain.patient;

import dao.Identified;
import domain.Entity;

public class Diagnosis extends Entity implements Identified<Integer> {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}
