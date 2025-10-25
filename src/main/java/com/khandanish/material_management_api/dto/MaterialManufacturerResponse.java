package com.khandanish.material_management_api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MaterialManufacturerResponse {
    private UUID materialManufacturerId;
    private String materialManufacturerName;
    private String materialManufacturerContactPerson;
    private String materialManufacturerContactNumber;
    private String materialManufacturerEmail;
}
