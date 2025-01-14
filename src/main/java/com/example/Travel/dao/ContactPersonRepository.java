package com.example.Travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Travel.model.ContactPerson;

public interface ContactPersonRepository extends JpaRepository<ContactPerson, Long> {

}
