package si.ape.authentication.models.converters;

import si.ape.authentication.models.entities.CityEntity;
import si.ape.authentication.lib.City;
import si.ape.authentication.models.entities.CountryEntity;

public class CityConverter {

    public static City toDto(CityEntity entity) {

        City dto = new City();
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setCountry(entity.getCountry() == null ? null : CountryConverter.toDto(entity.getCountry()));
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        return dto;

    }

    public static CityEntity toEntity(City dto) {

        CityEntity entity = new CityEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setCountry(CountryConverter.toEntity(dto.getCountry()));
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        return entity;

    }

}
