package com.ra.projectmd3test.repository.design;

import java.util.List;

public interface IGenericRepsitory <T,E>{
    List<T> findAll();
    T findById(E id);
    void save(T t);
    void deleteById(E t);
}
