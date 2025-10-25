package com.khandanish.material_management_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "unit_of_measurement", schema = "mdm_schema")
@Data
public class UnitOfMeasurement extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uom_id", updatable = false, nullable = false)
    private UUID uomId;

    @Column(name = "external_id", unique = true, nullable = false, insertable = false, updatable = false)
    private String externalId;

    @Column(name = "uom_name", nullable = false, length = 100)
    private String uomName;

    @Column(name = "uom_symbol", nullable = false, length = 20)
    private String uomSymbol;

    @Column(name = "uom_desc", length = 255)
    private String uomDesc;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
