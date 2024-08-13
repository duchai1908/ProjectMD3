package com.ra.projectmd3test.repository.design;

import com.ra.projectmd3test.model.entity.Image;
import com.ra.projectmd3test.model.entity.ProductDetail;

public interface IimageRepository extends IGenericRepsitory<Image,Integer> {
    void deleteByProductDetailId(ProductDetail productDetail);
}
