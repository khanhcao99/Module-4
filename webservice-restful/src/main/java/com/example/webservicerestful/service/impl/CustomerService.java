package com.example.webservicerestful.service.impl;


import com.example.webservicerestful.model.Customer;
import com.example.webservicerestful.repository.ICustomerRepository;
import com.example.webservicerestful.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class CustomerService implements ICustomerService {

    @Autowired
    public ICustomerRepository iCustomerRepository;


    @Override
    public Customer save(Customer customer) {
        return iCustomerRepository.save(customer);
    }

    @Override
    public void delete(long id) {
        iCustomerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(long id) {
        return iCustomerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

//    @Override
//    public List<Customer> findBySearch(String name) {
//        //        return iStudentRepository.findAllByNameContaining(name);
//        return iCustomerRepository.findByName("%" + name + "%");
//    }
//
//    @Override
//    public Page<Customer> findPageBySearch(String name, Pageable pageable) {
////        return provinceRepository.findByNamePage(name, pageable);
//                return iCustomerRepository.findAllByNameContaining(name, pageable);
//    }
//
//    @Override
//    public Page<Customer> findPage(Pageable pageable) {
//        return iCustomerRepository.findAll(pageable);
//    }
}
