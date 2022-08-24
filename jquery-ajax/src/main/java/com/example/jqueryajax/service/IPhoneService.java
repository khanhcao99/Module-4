package com.example.jqueryajax.service;

import com.example.jqueryajax.model.Phone;

import java.util.List;
import java.util.Optional;

public interface IPhoneService {
    List<Phone> findAll();

    Phone save(Phone phone);

    Optional<Phone> findById(long id);

    void delete(long id);
}
