package com.example.webservicerestful.controller;


import com.example.webservicerestful.model.Customer;
import com.example.webservicerestful.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    public ICustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomer(){
        List<Customer> customers = customerService.findAll();
        if (customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable("id") long id){
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable("id") long id, @RequestBody Customer customer){
        Optional<Customer> customer1 = customerService.findById(id);
        if (!customer1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(customerService.save(customer),HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer>deleteCustomer(@PathVariable("id") long id){
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            customerService.delete(id);
            return new ResponseEntity<>(customer.get(),HttpStatus.OK);
        }
    }
}
