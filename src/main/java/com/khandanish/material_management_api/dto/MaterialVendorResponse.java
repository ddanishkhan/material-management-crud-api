package com.khandanish.material_management_api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MaterialVendorResponse {
    private UUID materialVendorId;
    private String materialVendorName;
    private String materialVendorContactPerson;
    private String materialVendorContactNumber;
    private String materialVendorEmail;
}
