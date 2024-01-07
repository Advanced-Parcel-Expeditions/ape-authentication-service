package si.ape.authentication.models.converters;

import si.ape.authentication.models.entities.BranchEntity;
import si.ape.authentication.lib.Branch;

public class BranchConverter {

    public static Branch toDto(BranchEntity entity) {

        Branch dto = new Branch();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBranchType(entity.getBranchType() == null ? null : BranchTypeConverter.toDto(entity.getBranchType()));
        dto.setStreet(entity.getStreet() == null ? null : StreetConverter.toDto(entity.getStreet()));
        return dto;

    }

    public static BranchEntity toEntity(Branch dto) {

        BranchEntity entity = new BranchEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setBranchType(BranchTypeConverter.toEntity(dto.getBranchType()));
        entity.setStreet(StreetConverter.toEntity(dto.getStreet()));
        return entity;

    }

}
