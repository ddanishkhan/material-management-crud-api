package com.khandanish.material_management_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.UUID;

@Configuration
public class AuditingConfig {

    @Bean
    public AuditorAware<UUID> auditorAware() {
        return () -> Optional.of(UUID.fromString("a1b2c3d4-e5f6-7890-1234-567890abcdef")); // Replace with actual user ID from security context
    }
}
