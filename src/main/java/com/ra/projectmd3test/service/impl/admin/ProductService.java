package com.ra.projectmd3test.service.impl.admin;

import com.ra.projectmd3test.model.dto.admin.ProductRequest;
import com.ra.projectmd3test.model.entity.Category;
import com.ra.projectmd3test.model.entity.Product;
import com.ra.projectmd3test.repository.design.admin.IProductRepository;
import com.ra.projectmd3test.service.UploadService;
import com.ra.projectmd3test.service.design.admin.ICategoryService;
import com.ra.projectmd3test.service.design.admin.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private ICategoryService categoryService;
    @Override
    public List<Product> findAll(Integer page,Integer size) {
        Integer offset = page * size;
        return productRepository.findAll(offset,size);
    }

    @Override
    public Long getTotalProduct() {
        return productRepository.getTotalProduct();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(ProductRequest productRequest) {
        Product product = null;
        Category category = categoryService.findById(productRequest.getCategory_id());
        if(productRequest.getMainImage().getSize()>0 && category!=null){
            String imageMainUrl = uploadService.uploadFileToServer(productRequest.getMainImage());
            product = Product.builder()
                    .name(productRequest.getName())
                    .category(category)
                    .mainImage(imageMainUrl)
                    .description(productRequest.getDescription())
                    .selled(0)
                    .status(true)
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();
            productRepository.save(product);
        }
    }

    @Override
    public void saveProduct(ProductRequest productRequest, Integer productId,String mainImage) {
        Product oldProduct = productRepository.findById(productId);
        Product product = new Product();
        if(productRequest.getMainImage().getSize()>0){
            String imageMainUrl = uploadService.uploadFileToServer(productRequest.getMainImage());
            product.setMainImage(imageMainUrl);
        }else{
            product.setMainImage(mainImage);
        }
        product.setId(productId);
        product.setName(productRequest.getName());
        product.setCategory(categoryService.findById(productRequest.getCategory_id()));
        product.setDescription(productRequest.getDescription());
        product.setSelled(0);
        product.setStatus(true);
        product.setCreatedAt(oldProduct.getCreatedAt());
        product.setUpdatedAt(new Date());
        productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
