package com.ra.projectmd3test.service.design.admin;

import java.util.List;

public interface IGenericService <T,E>{
     List<T> findAll();
     T findById(E id);
     void save(T t);
     void deleteById(E t);
}
