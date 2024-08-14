package com.ra.projectmd3test.service.design;

import com.ra.projectmd3test.model.dto.ProductDetailRequest;
import com.ra.projectmd3test.model.entity.ProductDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IProductDetailService extends IGenericRequestService<ProductDetail,Integer, ProductDetailRequest> {
    void saveProductDetail(ProductDetailRequest productDetailRequest, List<MultipartFile> images);
    List<ProductDetail> getProductDetailByProductId(Integer productId,Integer page, Integer size);
    Long getTotalProductDetail();
    Map<Integer, List<String>> getProductImagesMap();
}
