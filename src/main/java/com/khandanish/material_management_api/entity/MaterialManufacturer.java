package com.khandanish.material_management_api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "material_manufacturer", schema = "mdm_schema")
@Data
public class MaterialManufacturer extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "material_manufacturer_id", updatable = false, nullable = false)
    private UUID materialManufacturerId;

    @Column(name = "external_id", unique = true, nullable = false, insertable = false, updatable = false)
    @Generated(GenerationTime.ALWAYS)
    private String externalId;

    @Column(name = "material_manufacturer_name", nullable = false, length = 100)
    private String materialManufacturerName;

    @Column(name = "material_manufacturer_contact_person", length = 100)
    private String materialManufacturerContactPerson;

    @Column(name = "material_manufacturer_contact_number", length = 15)
    private String materialManufacturerContactNumber;

    @Column(name = "material_manufacturer_email", length = 100)
    private String materialManufacturerEmail;

    @Column(name = "material_manufacturer_gst", length = 15)
    private String materialManufacturerGst;

    @Column(name = "material_manufacturer_desc", length = 255)
    private String materialManufacturerDesc;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
