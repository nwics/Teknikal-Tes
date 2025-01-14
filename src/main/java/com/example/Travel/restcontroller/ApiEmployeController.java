package com.example.Travel.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Travel.dao.EmployeRepository;
import com.example.Travel.service.EmployeService;

@RestController
@RequestMapping("/api/employe")
public class ApiEmployeController {

    @Autowired
    EmployeService employeService;

    @Autowired
    EmployeRepository employeRepository;

    @GetMapping("")
    public ResponseEntity<?> getDataEmploye(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "order", defaultValue = "") String order) {
        try {
            Map<String, Object> response = employeService.getAllDataEmploye(page, size, order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneEmploye(@PathVariable("id") long id) {
        return employeService.getOneEmployee(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getEmployeByName(@RequestParam("name") String name) {
        return employeService.getEmployeName(name);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteOneEmployee(@PathVariable("id") long id) {
        return employeService.deleteEmploye(id);
    }

    // @PostMapping("/add")

}
