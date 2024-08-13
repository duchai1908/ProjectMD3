package com.ra.projectmd3test.service.design;

import com.ra.projectmd3test.model.dto.ProductRequest;
import com.ra.projectmd3test.model.entity.Product;

public interface IProductService extends IGenericRequestService<Product,Integer, ProductRequest> {
    void saveProduct(ProductRequest productRequest, Integer productId,String url);
    Long getTotalProduct();
}
