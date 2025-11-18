package sn.dev.ct.core.domain.equipment.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sn.dev.ct.core.domain.equipment.entity.EquipmentState;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class EquipmentDTO {
    private Long id;
    private String name;
    private int totalQuantity;
    private int availableQuantity;
    private BigDecimal unitPrice;
    private String marque;
    private String serialNumber;
    private LocalDate acquisitionDate;
    private BigDecimal acquisitionPrice;
    private String description;
    @Enumerated(EnumType.STRING)
    private EquipmentState state;
    private Set<Long> categories;
}
