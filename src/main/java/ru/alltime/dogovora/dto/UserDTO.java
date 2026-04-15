package ru.alltime.dogovora.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
public class UserDTO{
        private UUID id;
        private String firstName;
        private String secondName;
        private String thirdName;
        private String position;
        private String login;
        private boolean isActive;
}
