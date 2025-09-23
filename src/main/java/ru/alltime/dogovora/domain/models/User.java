package ru.alltime.dogovora.domain.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String id;          // yes
    private boolean active;     // yes (признак не удален)
    private String login;       // yes
    private String firstName;   // yes
    private String secondName;  // yes
    private String thirdName;   // no
    private Roles role;        // yes (Dictionary: roles)
    private String position;    // no (свободная форма)
}
