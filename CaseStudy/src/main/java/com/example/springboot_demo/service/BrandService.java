package com.example.springboot_demo.service;

import com.example.springboot_demo.model.Brand;
import com.example.springboot_demo.repository.IBrandRepository;
import com.example.springboot_demo.service.impl.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private IBrandRepository iBrandRepository;

    @Override
    public List<Brand> findAll() {
        return iBrandRepository.findAll();
    }

    @Override
    public Optional<Brand> findById(long id) {
        return iBrandRepository.findById(id);
    }

    @Override
    public Brand save(Brand brand) {
        return iBrandRepository.save(brand);
    }

    @Override
    public void delete(long id) {
        iBrandRepository.deleteById(id);
    }
}
