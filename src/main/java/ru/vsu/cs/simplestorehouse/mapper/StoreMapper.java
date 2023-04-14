package ru.vsu.cs.simplestorehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.simplestorehouse.dto.StoreDto;
import ru.vsu.cs.simplestorehouse.entity.Store;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);
    StoreDto toDto(Store store);
    Store toEntity(StoreDto dto);
}
