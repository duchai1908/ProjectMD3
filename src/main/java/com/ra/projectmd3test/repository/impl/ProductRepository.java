package com.ra.projectmd3test.repository.impl;

import com.ra.projectmd3test.model.entity.Product;
import com.ra.projectmd3test.repository.design.IProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductRepository implements IProductRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll(Integer offset, Integer size) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Product> products = null;
        try {
            Query<Product> query = session.createQuery("from Product", Product.class);
            query.setFirstResult(offset);
            query.setMaxResults(size);
            products = query.getResultList();
            tx.commit();
        }catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return products;
    }

    @Override
    public Long getTotalProduct() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Long totalProduct = null;
        try{
            Query<Long> query = session.createQuery("select count(p) from Product p",Long.class);
            totalProduct = query.uniqueResult();
            tx.commit();
        }catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return totalProduct;
    }

    @Override

    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Product order by id",Product.class).list();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Product findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            Product product = session.get(Product.class, id);
            if(product != null) {
                return product;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public void save(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            if(product.getId() == null){
                session.save(product);
            }else{
                session.update(product);
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
    public void deleteById(Integer t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(session.get(Product.class, t));
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
