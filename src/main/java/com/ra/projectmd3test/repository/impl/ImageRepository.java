package com.ra.projectmd3test.repository.impl;

import com.ra.projectmd3test.model.entity.Image;
import com.ra.projectmd3test.model.entity.ProductDetail;
import com.ra.projectmd3test.repository.design.IimageRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepository implements IimageRepository {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Image> findAll() {
        Session session = sessionFactory.openSession();
        try{
            return session.createQuery("from Image",Image.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Image findById(Integer id) {
        Session session = sessionFactory.openSession();
        try{
            return session.get(Image.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public void save(Image image) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(image);
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

    }

    @Override
    public void deleteByProductDetailId(ProductDetail productDetail) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("delete from Image where productDetail.id = :productDetailId").setParameter("productDetailId", productDetail.getId());
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
