package si.ape.authentication.models.converters;

import si.ape.authentication.models.entities.CountryEntity;
import si.ape.authentication.lib.Country;

public class CountryConverter {

    public static Country toDto(CountryEntity entity) {

        Country dto = new Country();
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        return dto;

    }

    public static CountryEntity toEntity(Country dto) {

        CountryEntity entity = new CountryEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        return entity;

    }

}
