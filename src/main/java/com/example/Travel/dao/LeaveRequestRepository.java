package com.example.Travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Travel.model.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

}
