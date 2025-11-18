package sn.dev.ct.core.domain.equipment.handler;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import sn.dev.ct.core.domain.equipment.dto.CategoryDTO;
import sn.dev.ct.core.domain.equipment.dto.EquipmentDTO;
import sn.dev.ct.core.domain.equipment.entity.Category;
import sn.dev.ct.core.domain.equipment.entity.Equipment;
import sn.dev.ct.core.domain.equipment.mapper.CategoryMapper;
import sn.dev.ct.core.domain.equipment.mapper.EquipmentMapper;
import sn.dev.ct.core.domain.equipment.repository.CategoryRepository;
import sn.dev.ct.core.domain.equipment.repository.EquipmentRepository;
import sn.dev.ct.core.exceptions.ResourceNotFoundException;

import java.util.Set;

@Component
public class CategoryEquipmentHandler {
    private final CategoryRepository categoryRepository;
    private final EquipmentRepository equipmentRepository;
    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
    private final EquipmentMapper equipmentMapper = Mappers.getMapper(EquipmentMapper.class);

    public CategoryEquipmentHandler(CategoryRepository categoryRepository, EquipmentRepository equipmentRepository) {
        this.categoryRepository = categoryRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public Category saveOrUpdateCategory(CategoryDTO categoryDTO) {
        Category category = categoryDTO.getId() != null
                ? categoryRepository.findById(categoryDTO.getId()).orElse(new Category())
                : new Category();
        mapper.updateCategory(categoryDTO, category);

        return categoryRepository.save(category);
    }

    public Equipment saveOrUpdateEquipment(EquipmentDTO equipmentDTO) {
        Equipment equipment = equipmentDTO.getId() != null
                ? equipmentRepository.findById(equipmentDTO.getId()).orElse(new Equipment())
                : new Equipment();
        equipmentMapper.updateFromEquipmentDTO(equipmentDTO, equipment);
        return equipmentRepository.save(equipment);
    }

    public CategoryDTO addEquipment(Long categoryId, Long equipmentId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", categoryId)
        );
        Equipment equipment = equipmentRepository.findById(equipmentId).orElseThrow(
                () -> new ResourceNotFoundException("Equipment", equipmentId)
        );
        category.getEquipments().add(equipment);
        equipment.getCategories().add(category);
        categoryRepository.save(category);
        equipmentRepository.save(equipment);
        return mapper.toCategoryDTO(category);
    }

    public CategoryDTO addEquipments(Long categoryId, Set<Long> equipmentIds) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", categoryId)
        );
        var equipments = equipmentRepository.findAllById(equipmentIds);
        category.getEquipments().addAll(equipments);
        equipments.forEach(equipment -> {
            equipment.getCategories().add(category);
            equipmentRepository.save(equipment);}
        );
        categoryRepository.save(category);
        return mapper.toCategoryDTO(category);
    }

    public EquipmentDTO addCategories(Long equipmentId, Set<Long> categoryIds) {
        Equipment equipment = equipmentRepository.findById(equipmentId).orElseThrow(
                () -> new ResourceNotFoundException("Equipment", equipmentId)
        );
        var categories = categoryRepository.findAllById(categoryIds);
        equipment.getCategories().addAll(categories);
        categories.forEach(category -> {
            category.getEquipments().add(equipment);
            categoryRepository.save(category);
        });
        equipmentRepository.save(equipment);
        return equipmentMapper.toEquipmentDTO(equipment);
    }

    public CategoryDTO removeEquipment(Long categoryId, Long equipmentId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", categoryId)
        );
        Equipment equipment = equipmentRepository.findById(equipmentId).orElseThrow(
                () -> new ResourceNotFoundException("Equipment", equipmentId)
        );
        category.getEquipments().remove(equipment);
        equipment.getCategories().remove(category);
        categoryRepository.save(category);
        return mapper.toCategoryDTO(category);
    }
    public CategoryDTO removeEquipments(Long categoryId, Set<Long> equipmentIds) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", categoryId)
        );
        var equipments = equipmentRepository.findAllById(equipmentIds);
        category.getEquipments().removeAll(equipments);
        equipments.forEach(equipment -> {
            equipment.getCategories().remove(category);
            equipmentRepository.save(equipment);
        });
        categoryRepository.save(category);
        return mapper.toCategoryDTO(category);
    }

    public EquipmentDTO removeCategories(Long equipmentId, Set<Long> categoryIds) {
        Equipment equipment = equipmentRepository.findById(equipmentId).orElseThrow(
                () -> new ResourceNotFoundException("Equipment", equipmentId)
        );
        var categories = categoryRepository.findAllById(categoryIds);
        equipment.getCategories().removeAll(equipment.getCategories());
        categories.forEach(category -> {
            category.getEquipments().remove(equipment);
            categoryRepository.save(category);
        });
        equipmentRepository.save(equipment);
        return equipmentMapper.toEquipmentDTO(equipment);
    }
}
