package ru.alltime.dogovora.mapper;

import org.mapstruct.*;
import ru.alltime.dogovora.dto.UserRequestDTO;
import ru.alltime.dogovora.dto.UserResponseDTO;
import ru.alltime.dogovora.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    // ---- Request → Entity ----
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "MANAGER") // при регистрации всем назначаем MANAGER
    @Mapping(target = "active", constant = "true") // при создании пользователь активен
    User toEntity(UserRequestDTO dto);

    // ---- Entity → Response ----
    UserResponseDTO toResponseDto(User entity);

    // ---- Обновление сущности из DTO (опционально) ----
    // используется для patch/update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserRequestDTO dto, @MappingTarget User entity);
}
