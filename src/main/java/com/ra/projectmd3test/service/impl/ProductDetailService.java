package com.ra.projectmd3test.service.impl;

import com.ra.projectmd3test.model.dto.ProductDetailRequest;
import com.ra.projectmd3test.model.entity.*;
import com.ra.projectmd3test.repository.design.IProductDetailRepository;
import com.ra.projectmd3test.repository.design.IimageRepository;
import com.ra.projectmd3test.service.design.IColorService;
import com.ra.projectmd3test.service.design.IProductDetailService;
import com.ra.projectmd3test.service.design.IProductService;
import com.ra.projectmd3test.service.design.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private IimageRepository imageRepository;
    @Override
    public List<ProductDetail> findAll(Integer page, Integer size) {
        return IProductDetailRepository.findAll();
    }

    @Override
    public List<ProductDetail> getProductDetailByProductId(Integer productId, Integer page, Integer size) {
        Integer offset = page*size;
        return IProductDetailRepository.getProductDetailByProductId(productId, offset, size);
    }

    @Override
    public Long getTotalProductDetail() {
        return IProductDetailRepository.getTotalProductDetail();
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
    public Map<Integer, List<String>> getProductImagesMap() {
        // Lấy tất cả các hình ảnh từ repository
        List<Image> allImages = imageRepository.findAll();

        // Tạo Map để liên kết productDetailId với danh sách URL hình ảnh
        return allImages.stream()
                .collect(Collectors.groupingBy(
                        image -> image.getProductDetail().getId(),
                        Collectors.mapping(Image::getImageUrl, Collectors.toList())
                ));
    }
}
