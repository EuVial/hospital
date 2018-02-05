package domain.user;

public enum UserRole {
    ADMIN("role.admin"),
    DOCTOR("role.doctor"),
    NURSE("role.nurse");

    private String name;

    UserRole(final String name) {
        this.name = name;
    }

    public Integer getId() {
        return ordinal();
    }

    public String getName() {
        return name;
    }
}
