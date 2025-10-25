package com.khandanish.material_management_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResponse {
    private String message;
    private UUID materialId;
    private String externalId;
}
