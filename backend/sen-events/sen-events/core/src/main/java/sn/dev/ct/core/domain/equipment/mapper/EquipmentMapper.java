package sn.dev.ct.core.domain.equipment.mapper;

import org.mapstruct.*;
import sn.dev.ct.core.domain.equipment.dto.EquipmentDTO;
import sn.dev.ct.core.domain.equipment.entity.Equipment;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    @Mapping(target = "categories", expression = "java(equipment.getCategories().stream().map(e -> e.getId()).collect(java.util.stream.Collectors.toSet()))")
    EquipmentDTO toEquipmentDTO(Equipment equipment);
    @Mapping(target = "categories", ignore = true)
    Equipment toEquipment(EquipmentDTO equipmentDTO);

    @Mapping(target = "categories", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromEquipmentDTO(EquipmentDTO equipmentDTO, @MappingTarget Equipment equipment);
}
