package dao.entity.user;

public enum UserRole {
    ADMIN("role.admin"),
    DOCTOR("role.doctor"),
    NURSE("role.nurse");

    public static UserRole getByIdentity(Integer identity) {
        return UserRole.values()[identity];
    }

    private String name;

    private UserRole(String name) {
        this.name = name;
    }

    public Integer getId() {
        return ordinal();
    }

    public String getName() {
        return name;
    }
}
