package com.khandanish.material_management_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class MaterialRequest {

    @NotBlank(message = "Material name cannot be empty")
    @Size(max = 100, message = "Material name cannot exceed 100 characters")
    private String materialName;

    @NotNull(message = "Material rate per pack cannot be null")
    private BigDecimal materialRatePerPack;

    @NotNull(message = "Material pack size cannot be null")
    private BigDecimal materialPackSize;

    @NotNull(message = "Unit of measurement ID cannot be null")
    private UUID materialUomId;

    private UUID materialTypeId;
    private UUID materialManufacturerId;
    private UUID materialVendorId;

    @Size(max = 255, message = "Material description cannot exceed 255 characters")
    private String materialDesc;

}
