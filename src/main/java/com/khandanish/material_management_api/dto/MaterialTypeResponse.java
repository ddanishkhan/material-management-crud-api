package com.khandanish.material_management_api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MaterialTypeResponse {
    private UUID materialTypeId;
    private String materialTypeName;
    private String materialTypeDesc;
}
