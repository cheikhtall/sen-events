package sn.dev.ct.core.domain.equipment.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.dev.ct.core.domain.equipment.dto.CategoryDTO;
import sn.dev.ct.core.domain.equipment.entity.Category;
import sn.dev.ct.core.domain.equipment.handler.CategoryEquipmentHandler;
import sn.dev.ct.core.domain.equipment.mapper.CategoryMapper;
import sn.dev.ct.core.domain.equipment.repository.CategoryRepository;
import sn.dev.ct.core.exceptions.ResourceNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryEquipmentHandler categoryEquipmentHandler;
    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryEquipmentHandler categoryEquipmentHandler) {
        this.categoryRepository = categoryRepository;
        this.categoryEquipmentHandler = categoryEquipmentHandler;
    }
    @Override
    public CategoryDTO register(CategoryDTO categoryDTO) {
        return mapper.toCategoryDTO(categoryEquipmentHandler.saveOrUpdateCategory(categoryDTO));
    }

    @Override
    public CategoryDTO retrievesById(Long id) {
        return categoryRepository.findById(id).map(mapper::toCategoryDTO).orElseThrow(
                () -> new ResourceNotFoundException("Category", id)
        );
    }

    @Override
    public Page<CategoryDTO> retrieve(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(mapper::toCategoryDTO);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", id)
        );
        categoryRepository.delete(category);
    }
}
