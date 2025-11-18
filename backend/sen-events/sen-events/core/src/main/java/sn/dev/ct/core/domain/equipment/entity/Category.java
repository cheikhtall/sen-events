package sn.dev.ct.core.domain.equipment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sn.dev.ct.core.domain.base.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @SuperBuilder
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(length = 1000)
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Equipment> equipments = new HashSet<>();

    public Category() {}
}
