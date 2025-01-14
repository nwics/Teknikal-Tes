package com.example.Travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Travel.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

}
