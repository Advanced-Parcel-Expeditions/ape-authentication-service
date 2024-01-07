package si.ape.authentication.models.converters;

import si.ape.authentication.lib.User;
import si.ape.authentication.models.entities.UserEntity;

/**
 * The UserConverter class provides static methods for converting between User DTO and User Entity objects.
 */
public class UserConverter {

    /**
     * Converts a UserEntity object to a User DTO.
     *
     * @param entity The UserEntity object to convert.
     * @return The converted User DTO.
     */
    public static User toDto(UserEntity entity) {

        User dto = new User();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setRole(RoleConverter.toDto(entity.getRole()));

        return dto;

    }

    /**
     * Converts a User DTO to a UserEntity object.
     *
     * @param dto The User DTO to convert.
     * @return The converted UserEntity object.
     */
    public static UserEntity toEntity(User dto) {

        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRole(RoleConverter.toEntity(dto.getRole()));

        return entity;

    }

}