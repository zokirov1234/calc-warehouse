package com.company.model.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "attach")
public class AttachEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String extension;
    @Column(name = "original_name")
    private String originalName;
    private String fileName;
    @Column
    private Long size;
    @Column
    private String path;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

}
