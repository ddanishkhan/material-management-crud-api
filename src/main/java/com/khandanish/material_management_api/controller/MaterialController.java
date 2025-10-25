package com.khandanish.material_management_api.controller;

import com.khandanish.material_management_api.dto.DeleteResponse;
import com.khandanish.material_management_api.dto.MaterialRequest;
import com.khandanish.material_management_api.dto.MaterialResponse;
import com.khandanish.material_management_api.dto.MaterialUpdateRequest;
import com.khandanish.material_management_api.dto.PageResponse;
import com.khandanish.material_management_api.service.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping
    public ResponseEntity<PageResponse<MaterialResponse>> getAllActiveMaterials(@PageableDefault(size = 10, sort = "materialName") @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(materialService.getAllActiveMaterials(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponse> getMaterialById(@PathVariable UUID id) {
        return ResponseEntity.ok(materialService.getMaterialById(id));
    }

    @PostMapping
    public ResponseEntity<MaterialResponse> createMaterial(@Valid @RequestBody MaterialRequest request) {
        return new ResponseEntity<>(materialService.createMaterial(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponse> updateMaterial(@PathVariable UUID id, @Valid @RequestBody MaterialUpdateRequest request) {
        return ResponseEntity.ok(materialService.updateMaterial(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> softDeleteMaterial(@PathVariable UUID id) {
        DeleteResponse response = materialService.softDeleteMaterial(id);
        return ResponseEntity.ok(response);
    }
}
