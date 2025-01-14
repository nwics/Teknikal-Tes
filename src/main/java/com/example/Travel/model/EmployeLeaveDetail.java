package com.example.Travel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class EmployeLeaveDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    private String jenisCuti;
    private String alasanCuti;
    private String durasi;
    private String contact;

    public EmployeLeaveDetail() {
    }

    public EmployeLeaveDetail(Long id, String fullname, String jenisCuti, String alasanCuti, String durasi,
            String contact) {
        this.id = id;
        this.fullname = fullname;
        this.jenisCuti = jenisCuti;
        this.alasanCuti = alasanCuti;
        this.durasi = durasi;
        this.contact = contact;
    }

}
