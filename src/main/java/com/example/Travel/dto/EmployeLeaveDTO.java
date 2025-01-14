package com.example.Travel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeLeaveDTO {
    private Long id;
    private String fullname;
    private String jenisCuti;
    private String alasanCuti;
    private String durasi;
    private String contact;

    public EmployeLeaveDTO(Long id, String fullname, String jenisCuti, String alasanCuti, String durasi,
            String contact) {
        this.id = id;
        this.fullname = fullname;
        this.jenisCuti = jenisCuti;
        this.alasanCuti = alasanCuti;
        this.durasi = durasi;
        this.contact = contact;
    }

}
