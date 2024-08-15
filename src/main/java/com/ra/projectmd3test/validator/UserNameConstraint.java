package com.ra.projectmd3test.validator;

import com.ra.projectmd3test.repository.impl.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameConstraint implements ConstraintValidator<UserNameUnique, String> {
    @Autowired
    private UserRepository userRepository;
    // Kiểm tra xem có tồn tại tên đăng ký không, nếu đã tồn tại thì return false và ném lỗi ra binding result
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByName(s);
    }

    @Override
    public void initialize(UserNameUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
