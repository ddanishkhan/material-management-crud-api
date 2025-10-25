package com.khandanish.material_management_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MaterialResponse {

    private UUID materialId;
    private String externalId;
    private String materialName;
    private BigDecimal materialRatePerPack;
    private BigDecimal materialPackSize;
    private String materialDesc;
    private UomResponse uom;
    private MaterialTypeResponse type;
    private MaterialManufacturerResponse manufacturer;
    private MaterialVendorResponse vendor;
    private Boolean isActive;
    private UUID createdBy;
    private LocalDateTime createdOn;
    private UUID updatedBy;
    private LocalDateTime updatedOn;
}