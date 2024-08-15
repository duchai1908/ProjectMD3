package com.ra.projectmd3test.repository.impl.admin;

import com.ra.projectmd3test.model.entity.AdminUser;
import com.ra.projectmd3test.repository.design.admin.IAdminAccountRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminAccountRepository implements IAdminAccountRepository {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<AdminUser> findAll() {
        Session session = sessionFactory.openSession();
        try{
            return session.createQuery("from AdminUser order by id", AdminUser.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public AdminUser findById(Integer id) {
        Session session = sessionFactory.openSession();
        try{
            return session.get(AdminUser.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public void save(AdminUser adminUser) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        try{
            if(adminUser.getId() == null){
                session.save(adminUser);
            }
            ts.commit();
        }catch (Exception e){
            ts.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Integer t) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        try{
            AdminUser adminUser = session.get(AdminUser.class, t);
            session.delete(adminUser);
            ts.commit();
        }catch (Exception e){
            ts.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public Boolean existsByName(String name) {
        Session session = sessionFactory.openSession();
        try{
            List list = session.createQuery("from AdminUser where name = :name").setParameter("name", name).list();
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
    public AdminUser findByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from AdminUser where name = :name",AdminUser.class).setParameter("name",name).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
