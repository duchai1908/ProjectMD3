package com.ra.projectmd3test.repository.design.admin;

import com.ra.projectmd3test.model.entity.AdminUser;

public interface IAdminAccountRepository extends IGenericRepsitory<AdminUser,Integer> {
    Boolean existsByName(String name);
    AdminUser findByName(String name);
}
