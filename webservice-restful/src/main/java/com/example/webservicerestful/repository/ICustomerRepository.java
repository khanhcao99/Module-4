package com.example.webservicerestful.repository;


import com.example.webservicerestful.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
//    @Query(value = "select * from customer where first_name like :first", nativeQuery = true)
//    List<Customer> findByName(@Param("first") String first);
//
//    @Query(value = "select * from customer where first_name like :first", nativeQuery = true)
//    Page<Customer> findByNamePage(@Param("first") String first, Pageable pageable);
//
//    Page<Customer> findAllByNameContaining(String first, Pageable pageable);
}
