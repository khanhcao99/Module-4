package com.example.springboot_demo.service.impl;

import com.example.springboot_demo.model.Phone;

import java.util.List;

public interface IPhoneService extends ICRUD<Phone> {
    List<Phone> findByName(String name);

    List<Phone> findByBrands(long id);
}
