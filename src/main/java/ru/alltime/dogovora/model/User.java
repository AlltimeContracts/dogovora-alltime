package ru.alltime.dogovora.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String secondName;

    private String thirdName;

    @Column(nullable = false, unique = true)
    private String login;

    //TODO вставить в таблицу и написать в чат, чтобы снова удалили контейнеры, т.к.
    //реальных данных пока что нет
    private String password;

    private String position;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;   // для зарегистрировавшихся пользователей выдавать роль - MANAGER

    private boolean isActive;

}
   /* @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoryRecord> historyRecords; // быть не должно
*/