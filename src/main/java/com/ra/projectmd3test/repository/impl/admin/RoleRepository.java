package com.ra.projectmd3test.repository.impl.admin;

import com.ra.projectmd3test.model.entity.Role;
import com.ra.projectmd3test.model.entity.RoleName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public List<Role> getAllRole(){
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Role",Role.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
    public Role loadByRoleName(RoleName roleName) {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Role where roleName =:roleName", Role.class).setParameter("roleName", roleName).getSingleResult();
    }
    public Role findRoleById(Integer id){
        Session session = sessionFactory.openSession();
        try{
            return session.get(Role.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
