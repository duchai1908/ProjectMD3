package com.ra.projectmd3test.service.design;

import com.ra.projectmd3test.model.entity.Image;
import com.ra.projectmd3test.model.entity.ProductDetail;

public interface IimageService extends IGenericService<Image,Integer> {
    void deleteByProductDetailId(ProductDetail productDetail);
}
