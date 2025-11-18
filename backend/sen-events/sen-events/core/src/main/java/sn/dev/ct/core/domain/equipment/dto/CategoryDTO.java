package sn.dev.ct.core.domain.equipment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CategoryDTO {
   private Long id;
   @NotBlank(message = "Le nom ne peut pas être vide")
    private String name;
    private String description;
    private Set<Long> equipments;
}
