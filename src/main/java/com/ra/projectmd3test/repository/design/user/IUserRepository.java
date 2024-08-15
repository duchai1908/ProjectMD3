package com.ra.projectmd3test.repository.design.user;

import com.ra.projectmd3test.model.entity.User;
import com.ra.projectmd3test.service.design.admin.IGenericService;

public interface IUserRepository extends IGenericService<User,Integer> {
    Boolean existsByName(String name);
    User findByName(String name);
}
