package com.example.springboot_demo.service.impl;

import java.util.List;
import java.util.Optional;

public interface ICRUD<E> {
    List<E> findAll();

    Optional<E> findById(long id);

    E save(E e);

    void delete(long id);
}
