package com.khandanish.material_management_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "material_type", schema = "mdm_schema")
@Data
public class MaterialType extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "material_type_id", updatable = false, nullable = false)
    private UUID materialTypeId;

    @Column(name = "external_id", unique = true, nullable = false, insertable = false, updatable = false)
    private String externalId;

    @Column(name = "material_type_name", nullable = false, length = 100)
    private String materialTypeName;

    @Column(name = "material_type_desc", length = 255)
    private String materialTypeDesc;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
