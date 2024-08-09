package com.ra.projectmd3test.repository.impl;

import com.ra.projectmd3test.model.entity.Category;
import com.ra.projectmd3test.repository.design.ICategoryRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public class CategoryRepository implements ICategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();
        try{
            return session.createQuery("from Category",Category.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public void save(Category category) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (category.getId() == null) {
                category.setCreatedAt(new Date());
                category.setUpdatedAt(new Date());
                session.save(category);
            }else{
                category.setUpdatedAt(new Date());
                session.update(category);
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

    }
}
