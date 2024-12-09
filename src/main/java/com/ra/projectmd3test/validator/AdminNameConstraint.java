package com.ra.projectmd3test.validator;

import com.ra.projectmd3test.service.design.admin.IAdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdminNameConstraint implements ConstraintValidator<AdminNameUnique,String> {
    @Autowired
    private IAdminAccountService adminAccountService;
    @Override
    // Kiểm tra xem có tồn tại tên đăng ký không, nếu đã tồn tại thì return false và ném lỗi ra binding result
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !adminAccountService.existsByName(s);
    }

    @Override
    public void initialize(AdminNameUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
