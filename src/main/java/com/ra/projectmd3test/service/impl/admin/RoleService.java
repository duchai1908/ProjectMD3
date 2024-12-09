package com.ra.projectmd3test.service.impl.admin;

import com.ra.projectmd3test.model.entity.Role;
import com.ra.projectmd3test.model.entity.RoleName;
import com.ra.projectmd3test.repository.impl.admin.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAllRole(){
        return roleRepository.getAllRole();
    }
    public Role loadByRoleName(RoleName roleName) {
        return roleRepository.loadByRoleName(roleName);
    }
    public Role findRoleById(Integer id){
        return roleRepository.findRoleById(id);
    }
}
