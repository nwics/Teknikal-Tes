package com.example.Travel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "employee")
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nip", nullable = false, length = 5)
    private String nip;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "salary", precision = 10, scale = 0)
    private Double salary;

    @OneToOne
    @JoinColumn(name = "biodata_id", nullable = false)
    private Biodata biodata;
}
