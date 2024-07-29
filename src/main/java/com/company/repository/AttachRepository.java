package com.company.repository;

import com.company.model.entity.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachRepository extends JpaRepository<AttachEntity, Integer> {

    @Query("select attach from AttachEntity  attach where attach.fileName ilike ?1")
    AttachEntity findByFileName(String name);
}
