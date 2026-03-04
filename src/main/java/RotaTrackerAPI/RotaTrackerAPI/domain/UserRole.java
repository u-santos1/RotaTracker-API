package RotaTrackerAPI.RotaTrackerAPI.domain;

public enum UserRole {
    ADMIN("adimin"),
    MOTORISTA("motorista");

    private String role;

    UserRole(String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }
}
