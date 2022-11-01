package by.step.zimin.eshop.model;

public enum Role {
    USER("User authorize"),
    ADMIN("Admin authorize"),
    GUEST("default not authorize"),
    MANAGER("manager authorize");

    private final String role;



    private Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}