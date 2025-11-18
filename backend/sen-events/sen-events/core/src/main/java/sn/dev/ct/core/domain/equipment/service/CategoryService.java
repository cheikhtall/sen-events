package sn.dev.ct.core.domain.equipment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.dev.ct.core.domain.equipment.dto.CategoryDTO;

public interface CategoryService {
    CategoryDTO register(CategoryDTO categoryDTO);
    CategoryDTO retrievesById(Long id);
    Page<CategoryDTO> retrieve(Pageable pageable);
    void delete(Long id);
}
