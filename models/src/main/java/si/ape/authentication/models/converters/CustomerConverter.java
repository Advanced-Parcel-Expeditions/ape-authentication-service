package si.ape.authentication.models.converters;

import si.ape.authentication.lib.User;
import si.ape.authentication.models.entities.CustomerEntity;
import si.ape.authentication.lib.Customer;

public class CustomerConverter {

    public static Customer toDto(CustomerEntity entity) {

        Customer dto = new Customer();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setCompanyName(entity.getCompanyName());
        dto.setTelephoneNumber(entity.getTelephoneNumber());
        dto.setStreet(entity.getStreet() == null ? null : StreetConverter.toDto(entity.getStreet()));
        dto.setUser(entity.getUser() == null ? null : UserConverter.toDto(entity.getUser()));
        return dto;

    }

    public static CustomerEntity toEntity(Customer dto) {

        CustomerEntity entity = new CustomerEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setCompanyName(dto.getCompanyName());
        entity.setTelephoneNumber(dto.getTelephoneNumber());
        entity.setStreet(StreetConverter.toEntity(dto.getStreet()));
        entity.setUser(UserConverter.toEntity(dto.getUser()));
        return entity;

    }

}
