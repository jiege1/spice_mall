package vip.zihen.spice.workspace.user.entity;

public enum UserRole {

    CALLER(0, "访客"),
    USER(1, "用户"),
    ADMIN(2, "管理员");

    private final int role;
    private final String label;

    UserRole(int role, String label) {
        this.role = role;
        this.label = label;
    }

    public int getRole() {
        return role;
    }

    public String getLabel() {
        return label;
    }
}
