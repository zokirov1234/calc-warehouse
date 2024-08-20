package com.company.repository;

import com.company.model.entity.ClientGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationTypeRepository extends JpaRepository<ClientGroup, Integer> {
}
