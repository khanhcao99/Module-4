package com.example.springboot_demo.controller;


import com.example.springboot_demo.model.Brand;
import com.example.springboot_demo.model.Phone;
import com.example.springboot_demo.service.impl.IBrandService;
import com.example.springboot_demo.service.impl.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/phones")
public class PhoneController {
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private IPhoneService phoneService;

    @Autowired
    private IBrandService brandService;

    @GetMapping
    public ResponseEntity<List<Phone>> findAll(){
        return new ResponseEntity<>(phoneService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> findAllBrands(){
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }

    @GetMapping("id_brands/{id_brand}")
    public ResponseEntity<List<Phone>> findByBrands(@PathVariable("id_brand") long id){
        List<Phone> phoneList = phoneService.findByBrands(id);
        if (phoneList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(phoneList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Phone> detail(@PathVariable("id") long id){
        Optional<Phone> phoneOptional = phoneService.findById(id);
        if (phoneOptional.isPresent()){
            return new ResponseEntity<>(phoneOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Phone> createPhone(@RequestPart Phone phone, @RequestPart("fileImage") MultipartFile multipartFile){
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        phone.setImageUrl(fileName);
        Phone phoneSave = phoneService.save(phone);
        String uploadDir = "D:\\module_4\\CaseStudy\\src\\main\\resources\\static\\image";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)){
            try {
                Files.createDirectories(uploadPath);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        try {
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(filePath.toFile().getAbsolutePath());
        }catch (IOException e ){
            e.printStackTrace();
        }
        return new ResponseEntity<>(phoneSave, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Phone> update(@RequestPart Phone phone, @RequestPart("fileImage") MultipartFile multipartFile){
        Optional<Phone> phoneOptional = phoneService.findById(phone.getId());
        if (phoneOptional.isPresent()){
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            phone.setImageUrl(fileName);
            String uploadDir = "D:\\module_4\\CaseStudy\\src\\main\\resources\\static\\image\\";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)){
                try {
                    Files.createDirectories(uploadPath);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
            try {
                InputStream inputStream = multipartFile.getInputStream();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(filePath.toFile().getAbsolutePath());
            }catch (IOException e ){
                e.printStackTrace();
            }
            return new ResponseEntity<>(phoneService.save(phone), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        Optional<Phone> phoneOptional = phoneService.findById(id);
        if (phoneOptional.isPresent()){
            phoneService.delete(phoneOptional.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("search/{search}")
    public ResponseEntity<?> findByName(@PathVariable("search") String name){
        List<Phone> phoneList = phoneService.findByName(name);
        if (phoneList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(phoneList, HttpStatus.OK);
    }
}
