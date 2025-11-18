package sn.dev.ct.application.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import sn.dev.ct.core.domain.equipment.dto.CategoryDTO;
import sn.dev.ct.core.domain.equipment.dto.EquipmentDTO;
import sn.dev.ct.core.domain.equipment.service.CategoryService;
import sn.dev.ct.core.domain.equipment.service.EquipmentService;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentApi {
    private final EquipmentService equipmentService;
    private final CategoryService categoryService;
    public EquipmentApi(EquipmentService equipmentService, CategoryService categoryService) {
        this.equipmentService = equipmentService;
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/list")
    public Page<EquipmentDTO> getAllEquipments(
            @RequestParam(defaultValue = "0", name = "page")int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(defaultValue = "id", name = "sort") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        return equipmentService.retrieve(pageable);
    }

    @GetMapping("/categories/list")
    public Page<CategoryDTO> getCategories(
            @RequestParam(defaultValue = "0", name = "page")int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(defaultValue = "id", name = "sort") String sort

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        return categoryService.retrieve(pageable);
    }
}
