package sn.dev.ct.core.domain.equipment.mapper;

import org.mapstruct.*;
import sn.dev.ct.core.domain.equipment.dto.CategoryDTO;
import sn.dev.ct.core.domain.equipment.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "equipments", expression = "java(category.getEquipments().stream().map(e -> e.getId()).collect(java.util.stream.Collectors.toSet()))")
    CategoryDTO toCategoryDTO(Category category);
    @Mapping(target = "equipments", ignore = true)
    Category toCategory(CategoryDTO categoryDTO);
    @Mapping(target = "equipments", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategory(CategoryDTO categoryDTO, @MappingTarget  Category category);
}
