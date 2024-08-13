package com.ra.projectmd3test.service.design;

import com.ra.projectmd3test.model.dto.ProductDetailRequest;
import com.ra.projectmd3test.model.entity.ProductDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductDetailService extends IGenericRequestService<ProductDetail,Integer, ProductDetailRequest> {
    void saveProductDetail(ProductDetailRequest productDetailRequest, List<MultipartFile> images);
}
