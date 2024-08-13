package com.ra.projectmd3test.service.design;

import java.util.List;

public interface IGenericRequestService <T,E,R>{
    List<T> findAll(E page, E size);
    T findById(E id);
    void save(R r);
    void deleteById(E id);
}
