package com.nycu_epps.gameRatingBackEnd.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "developer")
@Data
public class DeveloperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
    private Integer developerId;

    @Column(name = "developer_name")
    private String developerName;
}
