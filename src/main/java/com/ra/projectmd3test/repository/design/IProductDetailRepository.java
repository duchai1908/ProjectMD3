package com.ra.projectmd3test.repository.design;

import com.ra.projectmd3test.model.dto.ProductDetailRequest;
import com.ra.projectmd3test.model.entity.ProductDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductDetailRepository extends IGenericRepsitory<ProductDetail,Integer>{
    void saveProductDetail(ProductDetail productDetail, List<MultipartFile> images);
    List<ProductDetail> getProductDetailByProductId(Integer productId,Integer offset, Integer size);
    Long getTotalProductDetail();
}
