package com.ra.projectmd3test.service.impl.admin;

import com.ra.projectmd3test.model.dto.admin.AdminLogin;
import com.ra.projectmd3test.model.dto.admin.AdminRegister;
import com.ra.projectmd3test.model.entity.AdminUser;
import com.ra.projectmd3test.model.entity.Role;
import com.ra.projectmd3test.repository.design.admin.IAdminAccountRepository;
import com.ra.projectmd3test.service.design.admin.IAdminAccountService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminAccountService implements IAdminAccountService {
    @Autowired
    private IAdminAccountRepository adminAccountRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private HttpSession session;
    @Override
    public List<AdminUser> findAll(Integer page, Integer size) {
        return null;
    }

    @Override
    public List<AdminUser> findAll() {
        return adminAccountRepository.findAll();
    }

    @Override
    public AdminUser findById(Integer id) {
        return adminAccountRepository.findById(id);
    }

    @Override
    public void save(AdminRegister adminRegister) {
        AdminUser adminUser = null;
        Set<Role> set = new HashSet<>();
        set.add(roleService.findRoleById(adminRegister.getRole()));
        adminUser = AdminUser.builder()
                .name(adminRegister.getName())
                .password(BCrypt.hashpw(adminRegister.getPassword(), BCrypt.gensalt(5)))
                .status(true)
                .roles(set)
                .build();
        adminAccountRepository.save(adminUser);
    }

    @Override
    public void deleteById(Integer id) {
        adminAccountRepository.deleteById(id);
    }

    @Override
    public Boolean existsByName(String name) {
        return adminAccountRepository.existsByName(name);
    }

    @Override
    public AdminUser findByName(String name) {
        return adminAccountRepository.findByName(name);
    }

    @Override
    public Boolean login(AdminLogin adminLogin) {
        try{
            AdminUser user = adminAccountRepository.findByName(adminLogin.getName());
            if(user != null){
                if(BCrypt.checkpw(adminLogin.getPassword(), user.getPassword())){
                    // luu thong tin nguoi dung vao session
                    session.setAttribute("adminLogin", user);
                    return true;
                }
            }
            return false;
        }catch (NoResultException e){
            return false;
        }
    }
}
