package com.example.webservicerestful.common;

import java.util.List;
import java.util.Optional;

public interface ICURDService<E> {
    E save(E e);

    void delete(long id);

    Optional<E> findById(long id);

    List<E> findAll();
}
