package com.khandanish.material_management_api.repository;

import com.khandanish.material_management_api.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {
    Page<Material> findByIsActiveTrue(Pageable pageable);
    Optional<Material> findByMaterialIdAndIsActiveTrue(UUID id);
}