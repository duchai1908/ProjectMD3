package com.ra.projectmd3test.service.impl.admin;

import com.ra.projectmd3test.model.dto.admin.ProductDetailRequest;
import com.ra.projectmd3test.model.entity.*;
import com.ra.projectmd3test.repository.design.admin.IProductDetailRepository;
import com.ra.projectmd3test.repository.design.admin.IimageRepository;
import com.ra.projectmd3test.repository.impl.admin.ProductDetailRepository;
import com.ra.projectmd3test.service.design.admin.IColorService;
import com.ra.projectmd3test.service.design.admin.IProductDetailService;
import com.ra.projectmd3test.service.design.admin.IProductService;
import com.ra.projectmd3test.service.design.admin.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
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
    @Autowired
    private ImageService imageService;
    @Autowired
    private ProductDetailRepository productDetailRepository;
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
    public void updateProductDetail(ProductDetailRequest productDetailRequest, List<MultipartFile> images, Integer productDetailId) {
        ProductDetail productDetail = IProductDetailRepository.findById(productDetailId);
        if (productDetail != null) {
            Size size = ISizeService.findById(productDetailRequest.getSize());
            Color color = IColorService.findById(productDetailRequest.getColor());
            Product product = productService.findById(productDetailRequest.getProduct());
            productDetail.setPrice(productDetailRequest.getPrice());
            productDetail.setColor(color);
            productDetail.setSize(size);
            productDetail.setQuantity(productDetailRequest.getQuantity());
            productDetail.setProduct(product);
            productDetail.setId(productDetailId);
            IProductDetailRepository.saveProductDetail(productDetail,images);
        }
    }

    @Override
    public void save(ProductDetailRequest productDetailRequest) {

    }

    @Override
    public void deleteById(Integer id) {
        IProductDetailRepository.deleteById(id);
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


    @Override
    public List<ProductDetail> getProductDetailByProductId(Integer productId) {
        return IProductDetailRepository.getProductDetailByProductId(productId);
    }

    //phantrang
    public List<ProductDetail> findWithPhanTrang(Integer page, Integer size) {
        Integer offset = page*size;
        return productDetailRepository.findAllByPhanTrang(offset,size);
    }


    @Override
    public String formatPrice(double price) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("vi-VN"));
        return numberFormat.format(price) + " VND";
    }

    //find one image with each product detail
    public Map<Integer, Image> getOneProductImageMap() {
        // Get all images from the image service
        List<Image> allImages = imageService.findAll();

        // Create a map of productDetailId to a single image
        return allImages.stream()
                .collect(Collectors.toMap(
                        image -> image.getProductDetail().getId(),
                        image -> image,
                        (existing, replacement) -> existing // Keep the first image found
                ));
    }

    //find all images with each product detail
    public Map<Integer, List<Image>> getAllProductImageMap(){

        // Create a map of productDetailId to all image
        return imageService.findAll().stream()
                .collect(Collectors.groupingBy(image -> image.getProductDetail().getId()));
    }

    //find product detail by product and category
    @Override
    public List<ProductDetail> findProductDetailsByCategoryId(Integer categoryId) {
        return productDetailRepository.findProductDetailsByCategoryId(categoryId);
    }
}
