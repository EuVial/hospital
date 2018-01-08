package domain.patient;

public enum  TreatmentType {
    MEDICAMENT("treatment.medicament"),
    PROCEDURE("treatment.procedure"),
    SURGERY("treatment.surgery");

    public static TreatmentType getByIdentity(Integer identity) {
        return TreatmentType.values()[identity];
    }

    private String name;

    TreatmentType(String name) {
        this.name = name;
    }

    public Integer getId() {
        return ordinal();
    }

    public String getName() {
        return name;
    }
}
