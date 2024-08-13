package com.ra.projectmd3test.repository.impl;

import com.ra.projectmd3test.model.entity.Color;
import com.ra.projectmd3test.repository.design.IColorRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ColorRepository implements IColorRepository {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Color> findAll() {
        Session session = sessionFactory.openSession();
        try{
            return session.createQuery("from Color order by id",Color.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Color findById(Integer id) {
        Session session = sessionFactory.openSession();
        try{
            Color color = session.get(Color.class, id);
            if(color != null){
                return color;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public void save(Color color) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try{
            if(color.getId() == null){
               color.setCreatedAt(new Date());
               color.setUpdateAt(new Date());
               session.save(color);
            }else{
                color.setUpdateAt(new Date());
                session.update(color);
            }
            tx.commit();
        }catch (Exception e){
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
        try {
            Color c = session.get(Color.class, t);
            if(c != null){
                session.delete(c);
            }
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
