package com.khandanish.material_management_api.service;

import com.khandanish.material_management_api.dto.MaterialManufacturerResponse;
import com.khandanish.material_management_api.dto.MaterialRequest;
import com.khandanish.material_management_api.dto.MaterialResponse;
import com.khandanish.material_management_api.dto.MaterialTypeResponse;
import com.khandanish.material_management_api.dto.MaterialUpdateRequest;
import com.khandanish.material_management_api.dto.MaterialVendorResponse;
import com.khandanish.material_management_api.dto.UomResponse;
import com.khandanish.material_management_api.entity.*;
import com.khandanish.material_management_api.exception.DuplicateMaterialNameException;
import com.khandanish.material_management_api.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.khandanish.material_management_api.dto.DeleteResponse;
import com.khandanish.material_management_api.dto.PageResponse;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final UnitOfMeasurementRepository unitOfMeasurementRepository;
    private final MaterialTypeRepository materialTypeRepository;
    private final MaterialManufacturerRepository materialManufacturerRepository;
    private final MaterialVendorRepository materialVendorRepository;

    public PageResponse<MaterialResponse> getAllActiveMaterials(Pageable pageable) {
        Page<Material> materialsPage = materialRepository.findByIsActiveTrue(pageable);
        List<MaterialResponse> content = materialsPage.getContent().stream()
                .map(this::mapToMaterialResponse)
                .collect(Collectors.toList());
        return new PageResponse<>(content, materialsPage.getNumber(), materialsPage.getSize(), materialsPage.getTotalElements(), materialsPage.getTotalPages(), materialsPage.isLast());
    }

    public MaterialResponse getMaterialById(UUID id) {
        Material material = materialRepository.findByMaterialIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Material not found with id: " + id));
        return mapToMaterialResponse(material);
    }

    @Transactional
    public MaterialResponse createMaterial(MaterialRequest request) {
        UnitOfMeasurement uom = unitOfMeasurementRepository.findById(request.getMaterialUomId())
                .orElseThrow(() -> new EntityNotFoundException("Unit of Measurement not found with id: " + request.getMaterialUomId()));

        MaterialType materialType = null;
        if (request.getMaterialTypeId() != null) {
            materialType = materialTypeRepository.findById(request.getMaterialTypeId())
                    .orElseThrow(() -> new EntityNotFoundException("Material Type not found with id: " + request.getMaterialTypeId()));
        }

        MaterialManufacturer manufacturer = null;
        if (request.getMaterialManufacturerId() != null) {
            manufacturer = materialManufacturerRepository.findById(request.getMaterialManufacturerId())
                    .orElseThrow(() -> new EntityNotFoundException("Material Manufacturer not found with id: " + request.getMaterialManufacturerId()));
        }

        MaterialVendor vendor = null;
        if (request.getMaterialVendorId() != null) {
            vendor = materialVendorRepository.findById(request.getMaterialVendorId())
                    .orElseThrow(() -> new EntityNotFoundException("Material Vendor not found with id: " + request.getMaterialVendorId()));
        }

        Material material = new Material();
        material.setMaterialName(request.getMaterialName());
        material.setMaterialRatePerPack(request.getMaterialRatePerPack());
        material.setMaterialPackSize(request.getMaterialPackSize());
        material.setUnitOfMeasurement(uom);
        material.setMaterialType(materialType);
        material.setMaterialManufacturer(manufacturer);
        material.setMaterialVendor(vendor);
        material.setMaterialDesc(request.getMaterialDesc());
        material.setIsActive(true);

        try {
            material = materialRepository.save(material);
            materialRepository.flush(); // Force flush to catch DataIntegrityViolationException immediately
        } catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getMostSpecificCause();
            if (cause instanceof SQLException) {
                SQLException sqlException = (SQLException) cause;
                if ("23505".equals(sqlException.getSQLState()) && sqlException.getMessage().contains("uq_material_name_active")) {
                    throw new DuplicateMaterialNameException(request.getMaterialName());
                }
            }
            throw ex;
        }
        return mapToMaterialResponse(material);
    }

    @Transactional
    public MaterialResponse updateMaterial(UUID id, MaterialUpdateRequest request) {
        Material material = materialRepository.findByMaterialIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Material not found with id: " + id));

        if (request.getMaterialName() != null) {
            material.setMaterialName(request.getMaterialName());
        }
        if (request.getMaterialRatePerPack() != null) {
            material.setMaterialRatePerPack(request.getMaterialRatePerPack());
        }
        if (request.getMaterialPackSize() != null) {
            material.setMaterialPackSize(request.getMaterialPackSize());
        }
        if (request.getMaterialUomId() != null) {
            UnitOfMeasurement uom = unitOfMeasurementRepository.findById(request.getMaterialUomId())
                    .orElseThrow(() -> new EntityNotFoundException("Unit of Measurement not found with id: " + request.getMaterialUomId()));
            material.setUnitOfMeasurement(uom);
        }
        if (request.getMaterialTypeId() != null) {
            MaterialType materialType = materialTypeRepository.findById(request.getMaterialTypeId())
                    .orElseThrow(() -> new EntityNotFoundException("Material Type not found with id: " + request.getMaterialTypeId()));
            material.setMaterialType(materialType);
        }
        if (request.getMaterialManufacturerId() != null) {
            MaterialManufacturer manufacturer = materialManufacturerRepository.findById(request.getMaterialManufacturerId())
                    .orElseThrow(() -> new EntityNotFoundException("Material Manufacturer not found with id: " + request.getMaterialManufacturerId()));
            material.setMaterialManufacturer(manufacturer);
        }
        if (request.getMaterialVendorId() != null) {
            MaterialVendor vendor = materialVendorRepository.findById(request.getMaterialVendorId())
                    .orElseThrow(() -> new EntityNotFoundException("Material Vendor not found with id: " + request.getMaterialVendorId()));
            material.setMaterialVendor(vendor);
        }
        if (request.getMaterialDesc() != null) {
            material.setMaterialDesc(request.getMaterialDesc());
        }

        try {
            material = materialRepository.save(material);
            materialRepository.flush(); // Force flush to catch DataIntegrityViolationException immediately
        } catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getMostSpecificCause();
            if (cause instanceof SQLException) {
                SQLException sqlException = (SQLException) cause;
                if ("23505".equals(sqlException.getSQLState()) && sqlException.getMessage().contains("uq_material_name_active")) {
                    throw new DuplicateMaterialNameException(request.getMaterialName());
                }
            }
            throw ex;
        }
        return mapToMaterialResponse(material);
    }

    @Transactional
    public DeleteResponse softDeleteMaterial(UUID id) {
        Material material = materialRepository.findByMaterialIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Material not found with id: " + id));
        material.setIsActive(false);
        materialRepository.save(material);
        return new DeleteResponse("Material deleted successfully", material.getMaterialId(), material.getExternalId());
    }

    private MaterialResponse mapToMaterialResponse(Material material) {
        MaterialResponse response = new MaterialResponse();
        response.setMaterialId(material.getMaterialId());
        response.setExternalId(material.getExternalId());
        response.setMaterialName(material.getMaterialName());
        response.setMaterialRatePerPack(material.getMaterialRatePerPack());
        response.setMaterialPackSize(material.getMaterialPackSize());
        response.setIsActive(material.getIsActive());
        response.setCreatedBy(material.getCreatedBy());
        response.setCreatedOn(material.getCreatedOn());
        response.setUpdatedBy(material.getUpdatedBy());
        response.setUpdatedOn(material.getUpdatedOn());
        response.setMaterialDesc(material.getMaterialDesc());

        if (material.getUnitOfMeasurement() != null) {
            UomResponse uomResponse = new UomResponse();
            uomResponse.setUomId(material.getUnitOfMeasurement().getUomId());
            uomResponse.setUomName(material.getUnitOfMeasurement().getUomName());
            uomResponse.setUomSymbol(material.getUnitOfMeasurement().getUomSymbol());
            response.setUom(uomResponse);
        }
        if (material.getMaterialType() != null) {
            MaterialTypeResponse materialTypeResponse = new MaterialTypeResponse();
            materialTypeResponse.setMaterialTypeId(material.getMaterialType().getMaterialTypeId());
            materialTypeResponse.setMaterialTypeName(material.getMaterialType().getMaterialTypeName());
            materialTypeResponse.setMaterialTypeDesc(material.getMaterialType().getMaterialTypeDesc());
            response.setType(materialTypeResponse);
        }
        if (material.getMaterialManufacturer() != null) {
            MaterialManufacturerResponse manufacturerResponse = new MaterialManufacturerResponse();
            manufacturerResponse.setMaterialManufacturerId(material.getMaterialManufacturer().getMaterialManufacturerId());
            manufacturerResponse.setMaterialManufacturerName(material.getMaterialManufacturer().getMaterialManufacturerName());
            manufacturerResponse.setMaterialManufacturerContactPerson(material.getMaterialManufacturer().getMaterialManufacturerContactPerson());
            manufacturerResponse.setMaterialManufacturerContactNumber(material.getMaterialManufacturer().getMaterialManufacturerContactNumber());
            manufacturerResponse.setMaterialManufacturerEmail(material.getMaterialManufacturer().getMaterialManufacturerEmail());
            response.setManufacturer(manufacturerResponse);
        }
        if (material.getMaterialVendor() != null) {
            MaterialVendorResponse vendorResponse = new MaterialVendorResponse();
            vendorResponse.setMaterialVendorId(material.getMaterialVendor().getMaterialVendorId());
            vendorResponse.setMaterialVendorName(material.getMaterialVendor().getMaterialVendorName());
            vendorResponse.setMaterialVendorContactPerson(material.getMaterialVendor().getMaterialVendorContactPerson());
            vendorResponse.setMaterialVendorContactNumber(material.getMaterialVendor().getMaterialVendorContactNumber());
            vendorResponse.setMaterialVendorEmail(material.getMaterialVendor().getMaterialVendorEmail());
            response.setVendor(vendorResponse);
        }
        return response;
    }
}