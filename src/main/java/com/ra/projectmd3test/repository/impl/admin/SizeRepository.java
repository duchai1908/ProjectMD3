package com.ra.projectmd3test.repository.impl.admin;

import com.ra.projectmd3test.model.entity.Size;
import com.ra.projectmd3test.repository.design.admin.ISizeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class SizeRepository implements ISizeRepository {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Size> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Size order by id",Size.class).list();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Size findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            Size size = session.get(Size.class, id);
            if(size != null) {
                return size;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public void save(Size size) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try{
            if(size.getId() == null) {
                size.setCreatedAt(new Date());
                size.setUpdatedAt(new Date());
                session.save(size);
            }else{
                size.setUpdatedAt(new Date());
                session.update(size);
            }
            tx.commit();
        }catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Integer t) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try{
            Size size = session.get(Size.class, t);
            if(size != null) {
                session.delete(size);
            }
            tx.commit();
        }catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
