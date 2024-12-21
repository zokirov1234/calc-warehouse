package com.company.model.entity;

import com.company.enums.AcceptanceStatus;
import com.company.enums.AcceptanceType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "acceptance")
@DynamicInsert
public class Acceptance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private AcceptanceType acceptanceType;

    private int warehouseId;

    private int counterpartyId;

    private Timestamp acceptanceDate;

    private int currencyId;

    @Enumerated(EnumType.STRING)
    private AcceptanceStatus acceptanceStatus;

    private String description;

    @Column(name = "state", columnDefinition = "boolean default true")
    private Boolean state;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
