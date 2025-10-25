package com.khandanish.material_management_api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UomResponse {
    private UUID uomId;
    private String uomName;
    private String uomSymbol;
}
