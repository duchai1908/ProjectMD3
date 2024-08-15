package com.ra.projectmd3test.service.impl.admin;

import com.ra.projectmd3test.model.entity.Image;
import com.ra.projectmd3test.model.entity.ProductDetail;
import com.ra.projectmd3test.repository.impl.admin.ImageRepository;
import com.ra.projectmd3test.service.design.admin.IimageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IimageService {
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findById(Integer id) {
        return imageRepository.findById(id);
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void deleteById(Integer t) {

    }

    @Override
    public void deleteByProductDetailId(ProductDetail productDetail) {
        imageRepository.deleteByProductDetailId(productDetail);
    }
}
