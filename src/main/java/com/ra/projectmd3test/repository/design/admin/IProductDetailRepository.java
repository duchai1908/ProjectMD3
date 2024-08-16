package com.ra.projectmd3test.repository.design.admin;

import com.ra.projectmd3test.model.entity.ProductDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductDetailRepository extends IGenericRepsitory<ProductDetail,Integer>{
    void saveProductDetail(ProductDetail productDetail, List<MultipartFile> images);
    List<ProductDetail> getProductDetailByProductId(Integer productId,Integer offset, Integer size);
    Long getTotalProductDetail();
    List<ProductDetail> getProductDetailByProductId(Integer productId);
    List<ProductDetail> findAllByPhanTrang(Integer offset, Integer size);
    public List<ProductDetail> findProductDetailsByCategoryId(Integer categoryId);
}
