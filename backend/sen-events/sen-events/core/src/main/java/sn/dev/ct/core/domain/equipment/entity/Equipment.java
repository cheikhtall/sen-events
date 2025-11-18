package sn.dev.ct.core.domain.equipment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sn.dev.ct.core.domain.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "equipments")
@Getter @Setter @SuperBuilder
public class Equipment extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int totalQuantity;
    @Column(nullable = false)
    private int availableQuantity;
    @Column(nullable = false)
    private BigDecimal unitPrice;
    @Column(nullable = false)
    private BigDecimal acquisitionPrice;
    private String marque;
    private String serialNumber;
    @Column(nullable = false)
    private LocalDate acquisitionDate;
    @Column(length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    private EquipmentState state;
    @ManyToMany
    @JoinTable(name = "equipment_categories",
    joinColumns = @JoinColumn(name = "equipment_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Equipment() {}

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getEquipments().add(this);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.getEquipments().remove(this);
    }
}
