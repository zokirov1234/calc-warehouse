package com.company.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "warehosue_material")
@Builder
@DynamicInsert
public class WareMaterialEntity {

    @Id
    private int id;

    private int warehouseId;

    private int materialId;

}
