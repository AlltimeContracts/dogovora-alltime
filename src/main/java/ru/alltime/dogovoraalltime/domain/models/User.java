package ru.alltime.dogovoraalltime.domain.models;

public class User {
    private int id;
    private boolean isActive;
    private String firstName;
    private String secondName;
    private String thirdName;
    private Roles role;
    private String position; // TEMP


    public User(int id, boolean isActive, String firstName, String secondName, String thirdName, Roles role, String position) {
        this.id = id;
        this.isActive = isActive;
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.role = role;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public Roles getRole() {
        return role;
    }

    public String getPosition() {
        return position;
    }
}
