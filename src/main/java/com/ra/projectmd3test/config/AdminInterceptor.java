package com.ra.projectmd3test.config;

import com.ra.projectmd3test.model.entity.AdminUser;
import com.ra.projectmd3test.model.entity.RoleName;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("adminLogin");
        if (adminUser == null) {
            response.sendRedirect("/loginadmin/login");
            return false;
        } else {
            if (adminUser.getRoles().stream().anyMatch(r -> r.getRoleName().equals(RoleName.ADMIN))) {
                return true;
            } else {
                response.sendRedirect("/adminaccount/403");
                return false;
            }
        }
    }
}
