package ru.alltime.dogovora.infrastructure.models;

import jakarta.persistence.*;
import lombok.*;
import ru.alltime.dogovora.domain.models.Roles;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "third_name")
    private String thirdName;

    @Enumerated(EnumType.STRING)               // <-- храним enum как текст
    @Column(name = "role", nullable = false)
    private Roles role;

    @Column(name = "position")
    private String position;
}