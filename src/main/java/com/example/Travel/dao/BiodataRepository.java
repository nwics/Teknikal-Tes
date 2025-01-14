package com.example.Travel.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Travel.model.Biodata;

public interface BiodataRepository extends JpaRepository<Biodata, Long> {

    @Query(value = "select concat(first_name,' ',last_name) as fullname where is_delete = false", nativeQuery = true)
    Optional<Biodata> findByFullName();

}
