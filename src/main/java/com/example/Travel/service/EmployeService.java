package com.example.Travel.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Travel.dao.BiodataRepository;
import com.example.Travel.dao.ContactPersonRepository;
import com.example.Travel.dao.EmployeLeaveDetailRepository;
import com.example.Travel.dao.EmployeRepository;
import com.example.Travel.dao.LeaveRepository;
import com.example.Travel.dao.LeaveRequestRepository;
import com.example.Travel.dto.EmployeLeaveDTO;
import com.example.Travel.model.EmployeLeave;
import com.example.Travel.model.EmployeLeaveDetail;
import com.example.Travel.model.Employee;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private BiodataRepository biodataRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeLeaveDetailRepository employeLeaveDetailRepository;

    public Map<String, Object> getAllDataEmploye(
            int page, int size, String order) {

        int perPage = (page - 1) * size;
        long total = employeRepository.countIsDelete();
        double totalPages = (double) total / size;

        Map<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("per_page", size);
        response.put("offset", perPage);
        response.put("total", total);
        response.put("total_pages", (int) Math.ceil(totalPages));

        List<EmployeLeaveDTO> data;
        if (order.equalsIgnoreCase("asc")) {
            data = employeRepository.findAllByOrderAsc(perPage, size).stream()
                    .map(row -> new EmployeLeaveDTO(
                            ((Number) row[0]).longValue(),
                            (String) row[1],
                            (String) row[2],
                            (String) row[3],
                            (String) row[4],
                            (String) row[5]))
                    .collect(Collectors.toList());
        } else if (order.equalsIgnoreCase("desc")) {
            data = employeRepository.findAllByOrderDesc(perPage, size).stream()
                    .map(row -> new EmployeLeaveDTO(
                            ((Number) row[0]).longValue(),
                            (String) row[1],
                            (String) row[2],
                            (String) row[3],
                            (String) row[4],
                            (String) row[5]))
                    .collect(Collectors.toList());
        } else {
            data = employeRepository.findAllDataEmploye(perPage, size).stream()
                    .map(row -> new EmployeLeaveDTO(
                            ((Number) row[0]).longValue(),
                            (String) row[1],
                            (String) row[2],
                            (String) row[3],
                            (String) row[4],
                            (String) row[5]))
                    .collect(Collectors.toList());
        }

        if (data.isEmpty()) {
            response.put("message", "Data Kosong");
        } else {
            response.put("data", data);
        }

        return response;
    }

    public ResponseEntity<?> getOneEmployee(@PathVariable("id") long id) {
        try {
            Optional<Employee> employe = employeRepository.findById(id);
            if (employe.isPresent()) {
                return ResponseEntity.ok(employe);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data dengan id " + id + " tidak ditemukan");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getEmployeName(@RequestParam("name") String name) {
        try {
            List<Employee> listData = employeRepository.findAllBySearch(name);
            if (listData.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("data dengan nama " + name + " tidak ditemukan");
            }
            return ResponseEntity.ok(listData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteEmploye(@PathVariable("id") long id) {
        try {
            Optional<Employee> listData = employeRepository.findById(id);
            if (listData.isPresent()) {
                Employee employe = listData.get();
                employe.setIsDelete(true);
                employeRepository.save(employe);
                return ResponseEntity.ok(employe);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data dengan id " + id + " tidak ditemukan");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> addEmployeeLeave(@RequestBody EmployeLeaveDTO employeLeaveDTO) {
        try {
            Map<String, Object> errors = new HashMap<>();

            if (employeLeaveDTO.getFullname() == null || employeLeaveDTO.getFullname().trim().isEmpty()) {
                errors.put("fullname", "Nama lengkap wajib diisi");
            }
            if (employeLeaveDTO.getJenisCuti() == null || employeLeaveDTO.getJenisCuti().trim().isEmpty()) {
                errors.put("jenisCuti", "Jenis cuti wajib diisi");
            }
            if (employeLeaveDTO.getAlasanCuti() == null || employeLeaveDTO.getAlasanCuti().trim().isEmpty()) {
                errors.put("alasanCuti", "Alasan cuti wajib diisi");
            }
            if (employeLeaveDTO.getDurasi() == null || employeLeaveDTO.getDurasi().trim().isEmpty()) {
                errors.put("durasi", "Durasi wajib diisi");
            }
            if (employeLeaveDTO.getContact() == null || employeLeaveDTO.getContact().trim().isEmpty()) {
                errors.put("contact", "Kontak wajib diisi");
            }

            if (!errors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
            }
            EmployeLeaveDetail employeLeave = new EmployeLeaveDetail(
                    employeLeaveDTO.getId(),
                    employeLeaveDTO.getFullname().trim(),
                    employeLeaveDTO.getJenisCuti().trim(),
                    employeLeaveDTO.getAlasanCuti().trim(),
                    employeLeaveDTO.getDurasi().trim(),
                    employeLeaveDTO.getContact().trim());

            EmployeLeaveDetail savedEmployeLeave = employeLeaveDetailRepository.save(employeLeave);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployeLeave);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
