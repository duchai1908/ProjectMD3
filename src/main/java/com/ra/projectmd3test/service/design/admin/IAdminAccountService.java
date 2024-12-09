package com.ra.projectmd3test.service.design.admin;

import com.ra.projectmd3test.model.dto.admin.AdminLogin;
import com.ra.projectmd3test.model.dto.admin.AdminRegister;
import com.ra.projectmd3test.model.entity.AdminUser;

import java.util.List;

public interface IAdminAccountService extends IGenericRequestService<AdminUser,Integer, AdminRegister>{
    List<AdminUser> findAll();
    Boolean existsByName(String name);
    AdminUser findByName(String name);
    Boolean login(AdminLogin adminLogin);
}
