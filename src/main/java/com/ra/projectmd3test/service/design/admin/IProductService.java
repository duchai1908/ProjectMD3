package com.ra.projectmd3test.service.design.admin;

import com.ra.projectmd3test.model.dto.admin.ProductRequest;
import com.ra.projectmd3test.model.entity.Product;

import java.util.List;

public interface IProductService extends IGenericRequestService<Product,Integer, ProductRequest> {
    void saveProduct(ProductRequest productRequest, Integer productId,String url);
    Long getTotalProduct();
    List<Product> findByCategoryId(Integer categoryId);
}
