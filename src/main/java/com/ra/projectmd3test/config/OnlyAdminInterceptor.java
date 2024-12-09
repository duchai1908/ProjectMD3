package com.ra.projectmd3test.config;

import com.ra.projectmd3test.model.entity.AdminUser;
import com.ra.projectmd3test.model.entity.RoleName;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OnlyAdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("adminLogin");
        if (adminUser == null) {
            response.sendRedirect("/loginadmin/login");
            return false;
        } else {
            if (adminUser.getRoles().stream().anyMatch(r -> r.getRoleName().equals(RoleName.ADMIN) || r.getRoleName().equals(RoleName.MODERATOR))) {
                return true;
            } else {
                response.sendRedirect("/adminaccount/403");
                return false;
            }
        }
    }
}
