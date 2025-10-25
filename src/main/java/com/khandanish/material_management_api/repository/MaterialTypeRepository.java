package com.khandanish.material_management_api.repository;

import com.khandanish.material_management_api.entity.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaterialTypeRepository extends JpaRepository<MaterialType, UUID> {
}
