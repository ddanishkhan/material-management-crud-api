package com.khandanish.material_management_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateMaterialNameException extends RuntimeException {
    private final String materialName;

    public DuplicateMaterialNameException(String materialName) {
        super(String.format("Material with name '%s' already exists.", materialName));
        this.materialName = materialName;
    }

    public String getMaterialName() {
        return materialName;
    }
}
