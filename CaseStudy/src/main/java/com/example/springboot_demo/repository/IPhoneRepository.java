package com.example.springboot_demo.repository;

import com.example.springboot_demo.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findAllByNameContaining(String name);

    List<Phone> findAllByBrand_Id(long id);

}
