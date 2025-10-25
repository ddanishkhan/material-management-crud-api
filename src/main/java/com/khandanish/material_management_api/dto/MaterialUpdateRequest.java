package com.khandanish.material_management_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class MaterialUpdateRequest {

    @Size(max = 100, message = "Material name cannot exceed 100 characters")
    private String materialName;

    private BigDecimal materialRatePerPack;

    private BigDecimal materialPackSize;

    private UUID materialUomId;

    private UUID materialTypeId;
    private UUID materialManufacturerId;
    private UUID materialVendorId;

    @Size(max = 255, message = "Material description cannot exceed 255 characters")
    private String materialDesc;

}
