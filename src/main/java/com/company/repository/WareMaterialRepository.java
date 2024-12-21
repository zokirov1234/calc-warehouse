package com.company.repository;

import com.company.model.entity.WareMaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WareMaterialRepository extends JpaRepository<WareMaterialEntity, Integer> {
}
