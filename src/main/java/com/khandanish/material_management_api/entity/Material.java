package com.khandanish.material_management_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "material", schema = "mdm_schema")
@Data
public class Material extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "material_id", updatable = false, nullable = false)
    private UUID materialId;

    @Column(name = "external_id", unique = true, nullable = false, insertable = false, updatable = false)
    @Generated(GenerationTime.ALWAYS)
    private String externalId;

    @Column(name = "material_name", nullable = false, length = 100)
    private String materialName;

    @Column(name = "material_rate_per_pack", nullable = false, precision = 18, scale = 3)
    private BigDecimal materialRatePerPack;

    @Column(name = "material_pack_size", nullable = false, precision = 18, scale = 3)
    private BigDecimal materialPackSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_uom_id", nullable = false)
    private UnitOfMeasurement unitOfMeasurement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_type_id")
    private MaterialType materialType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_manufacturer_id")
    private MaterialManufacturer materialManufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_vendor_id")
    private MaterialVendor materialVendor;

    @Column(name = "material_desc", length = 255)
    private String materialDesc;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Version
    @Column(name = "version")
    private Integer version;
}
