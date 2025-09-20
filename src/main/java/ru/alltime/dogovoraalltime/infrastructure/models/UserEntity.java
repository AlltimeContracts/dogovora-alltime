package ru.alltime.dogovoraalltime.infrastructure.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "isactive", nullable = false)
    private boolean isActive;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "secondname", nullable = false)
    private String secondName;

    @Column(name = "thirdname")
    private String thirdName;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "position")
    private String position;

    // --- Геттеры и сеттеры ---

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}