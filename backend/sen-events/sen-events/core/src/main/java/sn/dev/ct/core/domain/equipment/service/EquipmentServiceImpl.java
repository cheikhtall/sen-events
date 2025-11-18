package sn.dev.ct.core.domain.equipment.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.dev.ct.core.domain.equipment.dto.EquipmentDTO;
import sn.dev.ct.core.domain.equipment.entity.Equipment;
import sn.dev.ct.core.domain.equipment.handler.CategoryEquipmentHandler;
import sn.dev.ct.core.domain.equipment.mapper.EquipmentMapper;
import sn.dev.ct.core.domain.equipment.repository.CategoryRepository;
import sn.dev.ct.core.domain.equipment.repository.EquipmentRepository;
import sn.dev.ct.core.exceptions.ResourceNotFoundException;

@Service
public class EquipmentServiceImpl implements EquipmentService{
    private final EquipmentRepository equipmentRepository;
    private final CategoryEquipmentHandler categoryEquipmentHandler;
    private final EquipmentMapper mapper = Mappers.getMapper(EquipmentMapper.class);
    private final CategoryRepository categoryRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, CategoryEquipmentHandler categoryEquipmentHandler, CategoryRepository categoryRepository) {
        this.equipmentRepository = equipmentRepository;
        this.categoryEquipmentHandler = categoryEquipmentHandler;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public EquipmentDTO register(EquipmentDTO equipmentDTO) {
        Equipment equipment = categoryEquipmentHandler.saveOrUpdateEquipment(equipmentDTO);
        return mapper.toEquipmentDTO(equipment);
    }

    @Override
    public EquipmentDTO retrieves(Long id) {
        return equipmentRepository.findById(id)
                .map(mapper::toEquipmentDTO)
                .orElseThrow(
                () -> new ResourceNotFoundException("Equipment", id)
        );
    }

    @Override
    public Page<EquipmentDTO> retrieve(Pageable pageable) {
        return equipmentRepository.findAll(pageable).map(mapper::toEquipmentDTO);
    }

    @Override
    public void delete(Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment", id));
        equipmentRepository.delete(equipment);
    }
}
