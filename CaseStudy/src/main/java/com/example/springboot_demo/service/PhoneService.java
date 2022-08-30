package com.example.springboot_demo.service;

import com.example.springboot_demo.model.Phone;
import com.example.springboot_demo.repository.IPhoneRepository;
import com.example.springboot_demo.service.impl.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneService implements IPhoneService {
    @Autowired
    private IPhoneRepository iPhoneRepository;

    @Override
    public List<Phone> findAll() {
        return iPhoneRepository.findAll();
    }

    @Override
    public Optional<Phone> findById(long id) {
        return iPhoneRepository.findById(id);
    }

    @Override
    public Phone save(Phone phone) {
        return iPhoneRepository.save(phone);
    }

    @Override
    public void delete(long id) {
        iPhoneRepository.deleteById(id);
    }

    @Override
    public List<Phone> findByName(String name) {
        return iPhoneRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Phone> findByBrands(long id) {
        return iPhoneRepository.findAllByBrand_Id(id);
    }
}
