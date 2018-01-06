package dao.entity.patient;

import dao.entity.Entity;

public class Diagnosis extends Entity {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
