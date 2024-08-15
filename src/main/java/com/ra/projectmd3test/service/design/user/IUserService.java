package com.ra.projectmd3test.service.design.user;

import com.ra.projectmd3test.model.dto.admin.AdminLogin;
import com.ra.projectmd3test.model.dto.user.UserLogin;
import com.ra.projectmd3test.model.dto.user.UserRegister;
import com.ra.projectmd3test.model.entity.User;
import com.ra.projectmd3test.service.design.admin.IGenericRequestService;

public interface IUserService extends IGenericRequestService<User,Integer, UserRegister> {
    Boolean login(UserLogin userLogin);
}
