package com.ra.projectmd3test.repository.impl;

import com.ra.projectmd3test.model.entity.Image;
import com.ra.projectmd3test.model.entity.ProductDetail;
import com.ra.projectmd3test.repository.design.IProductDetailRepository;
import com.ra.projectmd3test.service.UploadService;
import com.ra.projectmd3test.service.impl.ImageService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public class ProductDetailRepository implements IProductDetailRepository {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private ImageService imageService;
    @Override
    public void saveProductDetail(ProductDetail productDetail, List<MultipartFile> images) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            if(productDetail.getId() == null && images.size() > 0){
                session.save(productDetail);
                for (MultipartFile image : images) {
                    Image newimg = new Image();
                    newimg.setProductDetail(productDetail);
                    newimg.setImageUrl(uploadService.uploadFileToServer(image));
                    imageService.save(newimg);
                }
            }else{
                session.save(productDetail);
            }
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public List<ProductDetail> getProductDetailByProductId(Integer productId, Integer offset, Integer size) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<ProductDetail> productDetails = null;
        try{
            Query<ProductDetail> query = session.createQuery("from ProductDetail where product.id  = :productId", ProductDetail.class).setParameter("productId", productId);
            query.setFirstResult(offset);
            query.setMaxResults(size);
            productDetails = query.list();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return productDetails;
    }

    @Override
    public Long getTotalProductDetail() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long totalProductDetail = null;
        try{
            Query<Long> query = session.createQuery("select count(*) from ProductDetail", Long.class);
            totalProductDetail = query.getSingleResult();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return totalProductDetail;
    }

    @Override
    public List<ProductDetail> findAll() {
        Session session = sessionFactory.openSession();
        try{
            return session.createQuery("from ProductDetail",ProductDetail.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public ProductDetail findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            ProductDetail productDetail = session.get(ProductDetail.class, id);
            if(productDetail != null){
                return productDetail;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public void save(ProductDetail productDetail) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            if(productDetail.getId() == null){
                session.save(productDetail);
            }else{
                session.update(productDetail);
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Integer t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            ProductDetail productDetail = session.get(ProductDetail.class, t);
            session.delete(productDetail);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
