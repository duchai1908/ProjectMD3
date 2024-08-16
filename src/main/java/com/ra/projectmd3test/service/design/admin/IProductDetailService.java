package com.ra.projectmd3test.service.design.admin;

import com.ra.projectmd3test.model.dto.admin.ProductDetailRequest;
import com.ra.projectmd3test.model.entity.ProductDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IProductDetailService extends IGenericRequestService<ProductDetail,Integer, ProductDetailRequest> {
    void saveProductDetail(ProductDetailRequest productDetailRequest, List<MultipartFile> images);
    List<ProductDetail> getProductDetailByProductId(Integer productId,Integer page, Integer size);
    Long getTotalProductDetail();
    Map<Integer, List<String>> getProductImagesMap();
    void updateProductDetail(ProductDetailRequest productDetailRequest, List<MultipartFile> images, Integer productDetailId);
    List<ProductDetail> getProductDetailByProductId(Integer productId);
    //format price
    public String formatPrice(double price);
    //find list product detail by category id
    public List<ProductDetail> findProductDetailsByCategoryId(Integer categoryId);
}
