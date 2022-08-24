package com.example.jqueryajax.service.impl;

import com.example.jqueryajax.model.Phone;
import com.example.jqueryajax.repository.IPhoneRepository;
import com.example.jqueryajax.service.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneService implements IPhoneService {
    @Autowired
    private IPhoneRepository phoneRepository;

    @Override
    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    @Override
    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public Optional<Phone> findById(long id) {
        return phoneRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        phoneRepository.deleteById(id);
    }
}
