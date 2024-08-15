package com.ra.projectmd3test.repository.impl.user;

import com.ra.projectmd3test.model.entity.AdminUser;
import com.ra.projectmd3test.model.entity.User;
import com.ra.projectmd3test.repository.design.user.IUserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        try{
            return session.createQuery("from User",User.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public User findById(Integer id) {
        Session session = sessionFactory.openSession();
        try{
            return session.get(User.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(user);
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
            session.delete(session.get(User.class, t));
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public Boolean existsByName(String name) {
        Session session = sessionFactory.openSession();
        try{
            List list = session.createQuery("from User where userName = :name").setParameter("name", name).list();
            if(list.size() > 0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public User findByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from User where userName = :name", User.class).setParameter("name",name).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
