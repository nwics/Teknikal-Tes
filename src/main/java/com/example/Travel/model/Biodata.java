package com.example.Travel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "biodata")
public class Biodata extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "dob")
    private String dob;

    @Column(name = "pob", length = 50)
    private String pob;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "marital_status")
    private Boolean maritalStatus;

    // @OneToMany
    // @JoinColumn(name = "family_id", nullable = false, insertable = false,
    // referencedColumnName = "id")
    // private Family family;

    // @OneToMany
    // @JoinColumn(name = "contact_person_id", referencedColumnName = "id", nullable
    // = false, insertable = false)
    // private ContactPerson contactPersonId;

    // Getters and Setters
}
