package com.ra.projectmd3test.repository.design;

import com.ra.projectmd3test.model.entity.Product;
import com.ra.projectmd3test.service.design.IGenericService;

import java.util.List;

public interface IProductRepository extends IGenericService<Product,Integer> {
    List<Product> findAll(Integer offset, Integer size);
    Long getTotalProduct();
}
