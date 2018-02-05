package domain.patient;

public enum  TreatmentType {
    MEDICAMENT("treatment.medicament"),
    PROCEDURE("treatment.procedure"),
    SURGERY("treatment.surgery");

    public static TreatmentType getByIdentity(final Integer identity) {
        return TreatmentType.values()[identity];
    }

    private String name;

    TreatmentType(final String name) {
        this.name = name;
    }

    public Integer getId() {
        return ordinal();
    }

    public String getName() {
        return name;
    }
}
