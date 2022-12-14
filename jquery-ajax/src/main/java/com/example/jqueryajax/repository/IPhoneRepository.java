package com.example.jqueryajax.repository;

import com.example.jqueryajax.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhoneRepository extends JpaRepository<Phone, Long> {

}
