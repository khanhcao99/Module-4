package com.example.webservicerestful.service;


import com.example.webservicerestful.common.ICURDService;
import com.example.webservicerestful.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService extends ICURDService<Customer> {
//    List<Customer> findBySearch(String name);
//
//    Page<Customer> findPageBySearch(String name, Pageable pageable);
//
//    Page<Customer> findPage(Pageable pageable);
}
