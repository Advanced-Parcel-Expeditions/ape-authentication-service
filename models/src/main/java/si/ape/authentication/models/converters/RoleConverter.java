package si.ape.authentication.models.converters;

import si.ape.authentication.lib.Role;
import si.ape.authentication.models.entities.RoleEntity;

/**
 * The RoleConverter class provides static methods for converting between Role DTO and Role Entity objects.
 */
public class RoleConverter {

    /**
     * Converts a RoleEntity object to a Role DTO.
     *
     * @param entity The RoleEntity object to convert.
     * @return The converted Role DTO.
     */
    public static Role toDto(RoleEntity entity) {

        Role dto = new Role();
        dto.setId(entity.getId());
        dto.setRoleName(entity.getRoleName());
        return dto;

    }

    /**
     * Converts a Role DTO to a RoleEntity object.
     *
     * @param dto The Role DTO to convert.
     * @return The converted RoleEntity object.
     */
    public static RoleEntity toEntity(Role dto) {

        RoleEntity entity = new RoleEntity();
        entity.setId(dto.getId());
        entity.setRoleName(dto.getRoleName());
        return entity;

    }

}