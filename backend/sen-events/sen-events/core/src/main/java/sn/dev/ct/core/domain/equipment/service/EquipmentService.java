package sn.dev.ct.core.domain.equipment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.dev.ct.core.domain.equipment.dto.EquipmentDTO;

public interface EquipmentService {

    EquipmentDTO register(EquipmentDTO equipmentDTO);
    EquipmentDTO retrieves(Long id);
    Page<EquipmentDTO> retrieve(Pageable pageable);
    void delete(Long id);
}
