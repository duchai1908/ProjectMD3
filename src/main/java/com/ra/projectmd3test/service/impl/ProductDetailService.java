package com.ra.projectmd3test.service.impl;

import com.ra.projectmd3test.model.dto.ProductDetailRequest;
import com.ra.projectmd3test.model.entity.Color;
import com.ra.projectmd3test.model.entity.Product;
import com.ra.projectmd3test.model.entity.ProductDetail;
import com.ra.projectmd3test.model.entity.Size;
import com.ra.projectmd3test.repository.design.IProductDetailRepository;
import com.ra.projectmd3test.service.design.IColorService;
import com.ra.projectmd3test.service.design.IProductDetailService;
import com.ra.projectmd3test.service.design.IProductService;
import com.ra.projectmd3test.service.design.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductDetailService implements IProductDetailService {
    @Autowired
    private IProductDetailRepository IProductDetailRepository;
    @Autowired
    private ISizeService ISizeService;
    @Autowired
    private IColorService IColorService;
    @Autowired
    private IProductService productService;
    @Override
    public List<ProductDetail> findAll(Integer page, Integer size) {
        return IProductDetailRepository.findAll();
    }

    @Override
    public ProductDetail findById(Integer id) {
        return IProductDetailRepository.findById(id);
    }

    @Override
    public void saveProductDetail(ProductDetailRequest productDetailRequest, List<MultipartFile> images) {
        ProductDetail productDetail = null;
        Size size = ISizeService.findById(productDetailRequest.getSize());
        Color color = IColorService.findById(productDetailRequest.getColor());
        Product product = productService.findById(productDetailRequest.getProduct());
        productDetail = ProductDetail.builder()
                .price(productDetailRequest.getPrice())
                .color(color)
                .size(size)
                .quantity(productDetailRequest.getQuantity())
                .product(product)
                .build();
        IProductDetailRepository.saveProductDetail(productDetail,images);
    }

    @Override
    public void save(ProductDetailRequest productDetailRequest) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
