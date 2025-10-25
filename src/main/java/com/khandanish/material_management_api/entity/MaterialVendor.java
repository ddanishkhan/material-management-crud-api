package com.khandanish.material_management_api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "material_vendor", schema = "mdm_schema")
@Data
public class MaterialVendor extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "material_vendor_id", updatable = false, nullable = false)
    private UUID materialVendorId;

    @Column(name = "external_id", unique = true, nullable = false, insertable = false, updatable = false)
    @Generated(GenerationTime.ALWAYS)
    private String externalId;

    @Column(name = "material_vendor_name", nullable = false, length = 100)
    private String materialVendorName;

    @Column(name = "material_vendor_contact_person", length = 100)
    private String materialVendorContactPerson;

    @Column(name = "material_vendor_contact_number", length = 15)
    private String materialVendorContactNumber;

    @Column(name = "material_vendor_email", length = 100)
    private String materialVendorEmail;

    @Column(name = "material_vendor_gst", length = 15)
    private String materialVendorGst;

    @Column(name = "material_vendor_desc", length = 255)
    private String materialVendorDesc;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
