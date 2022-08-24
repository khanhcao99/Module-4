package com.example.jqueryajax.controller;

import com.example.jqueryajax.model.Category;
import com.example.jqueryajax.model.Phone;
import com.example.jqueryajax.service.ICategoryService;
import com.example.jqueryajax.service.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/phones")
public class PhoneController {
    @Autowired
    private IPhoneService iPhoneService;

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping
    public ResponseEntity<List<Phone>> findAll(){
        return new ResponseEntity<>(iPhoneService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAllCategories(){
        return new ResponseEntity<>(iCategoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Phone> detail(@PathVariable("id") long id){
        Optional<Phone> phoneOptional = iPhoneService.findById(id);
        if (phoneOptional.isPresent()){
            return new ResponseEntity<>(phoneOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Phone> createPhone(@RequestBody Phone phone){
        return new ResponseEntity<>(iPhoneService.save(phone), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Phone> update(@RequestBody Phone phone){
        Optional<Phone> phoneOptional = iPhoneService.findById(phone.getId());
        if (phoneOptional.isPresent()){
            return  new ResponseEntity<>(iPhoneService.save(phone), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        Optional<Phone> phoneOptional = iPhoneService.findById(id);
        if (phoneOptional.isPresent()){
            iPhoneService.delete(phoneOptional.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
