package domain.patient;

import dao.Identified;
import domain.Entity;

import java.util.Date;

public class Diagnosis extends Entity implements Identified<Integer> {
    private String title;
    private Date consultationDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }
}
