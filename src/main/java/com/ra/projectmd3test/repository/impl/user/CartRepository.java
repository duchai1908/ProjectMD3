package com.ra.projectmd3test.repository.impl.user;

import com.ra.projectmd3test.model.entity.CartItem;
import com.ra.projectmd3test.repository.design.user.ICartRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepository implements ICartRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CartItem> findByUserId(Integer userId) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from CartItem where user.id = :userId", CartItem.class)
                    .setParameter("userId", userId)
                    .list();
        } finally {
            session.close();
        }
    }

    @Override
    public CartItem findByUserIdAndProductId(Integer userId, Integer productId) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from CartItem where user.id = :userId and product.id = :productId", CartItem.class)
                    .setParameter("userId", userId)
                    .setParameter("productId", productId)
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    @Override
    public CartItem save(CartItem cartItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(cartItem);
            transaction.commit();
            return cartItem;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            CartItem cartItem = session.load(CartItem.class, id);
            session.delete(cartItem);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteAllByUserId(Integer userId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createQuery("delete from CartItem where user.id = :userId")
                    .setParameter("userId", userId)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
