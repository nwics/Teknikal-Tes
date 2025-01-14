package com.example.Travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Travel.model.Employee;

public interface EmployeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select count(id) from employee where is_delete = false", nativeQuery = true)
    Long countIsDelete();

    @Query(value = """
            select e.id as id,
                   concat(b.first_name, ' ', b.last_name) as fullname,
                   l.name as jenisCuti,
                   lr.reason as alasanCuti,
                   age(lr.end_date, lr.start_date)::text as durasi,
                   cp.contact as contact
            from employee e
            join biodata b on e.biodata_id = b.id
            join contact_person cp on b.id = cp.biodata_id
            join leave_request lr on e.id = lr.employee_id
            join leave l on lr.leave_id = l.id
            where e.is_delete = false
            OFFSET :page LIMIT :size
            """, nativeQuery = true)
    List<Object[]> findAllDataEmploye(@Param("page") int page, @Param("size") int size);

    @Query(value = """
            select e.id as id,
                   concat(b.first_name, ' ', b.last_name) as fullname,
                   l.name as jenisCuti,
                   lr.reason as alasanCuti,
                   age(lr.end_date, lr.start_date)::text as durasi,
                   cp.contact as contact
            from employee e
            join biodata b on e.biodata_id = b.id
            join contact_person cp on b.id = cp.biodata_id
            join leave_request lr on e.id = lr.employee_id
            join leave l on lr.leave_id = l.id
            where e.is_delete = false
            order by b.first_name ASC
            OFFSET :page LIMIT :size
            """, nativeQuery = true)
    List<Object[]> findAllByOrderAsc(@Param("page") int page, @Param("size") int size);

    @Query(value = """
            select e.id as id,
                   concat(b.first_name, ' ', b.last_name) as fullname,
                   l.name as jenisCuti,
                   lr.reason as alasanCuti,
                   age(lr.end_date, lr.start_date)::text as durasi,
                   cp.contact as contact
            from employee e
            join biodata b on e.biodata_id = b.id
            join contact_person cp on b.id = cp.biodata_id
            join leave_request lr on e.id = lr.employee_id
            join leave l on lr.leave_id = l.id
            where e.is_delete = false
            order by b.first_name DESC
            OFFSET :page LIMIT :size
            """, nativeQuery = true)
    List<Object[]> findAllByOrderDesc(@Param("page") int page, @Param("size") int size);

    @Query(value = "SELECT * FROM employee WHERE name ILIKE %:name% AND is_delete = FALSE", nativeQuery = true)
    List<Employee> findAllBySearch(@Param("name") String name);

}
