package com.ra.projectmd3test.repository.impl;

import com.ra.projectmd3test.model.entity.Banner;
import com.ra.projectmd3test.repository.design.IBannerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerRepository implements IBannerRepository {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Banner> findAll() {
        Session session = sessionFactory.openSession();
        try{
            return session.createQuery("from Banner", Banner.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Banner findById(Integer id) {
        Session session = sessionFactory.openSession();
        try{
            Banner banner = session.get(Banner.class, id);
            if(banner != null){
                return banner;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public void save(Banner banner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (banner.getId() == null) {
                session.save(banner);
            }else{
                session.update(banner);
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
        try{
            Banner banner = session.get(Banner.class, t);
            if(banner != null){
                session.delete(banner);
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

}
