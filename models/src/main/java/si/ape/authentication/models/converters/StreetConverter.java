package si.ape.authentication.models.converters;

import si.ape.authentication.models.entities.StreetEntity;
import si.ape.authentication.lib.Street;

public class StreetConverter {

    public static Street toDto(StreetEntity entity) {

        Street dto = new Street();
        dto.setStreetName(entity.getStreetName());
        dto.setStreetNumber(entity.getStreetNumber());
        dto.setCity(entity.getCity() == null ? null : CityConverter.toDto(entity.getCity()));
        return dto;

    }

    public static StreetEntity toEntity(Street dto) {

        StreetEntity entity = new StreetEntity();
        entity.setStreetName(dto.getStreetName());
        entity.setStreetNumber(dto.getStreetNumber());
        entity.setCity(dto.getCity() == null ? null : CityConverter.toEntity(dto.getCity()));
        return entity;

    }


}
