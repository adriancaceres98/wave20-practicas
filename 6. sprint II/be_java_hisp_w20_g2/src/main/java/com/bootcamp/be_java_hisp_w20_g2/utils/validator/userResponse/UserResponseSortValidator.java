package com.bootcamp.be_java_hisp_w20_g2.utils.validator.userResponse;

import com.bootcamp.be_java_hisp_w20_g2.utils.sort.UserResponseDTOStreamSorter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UserResponseSortValidator implements ConstraintValidator<UserResponseSorterConstraint, Optional<String>> {
    @Override
    public boolean isValid(Optional<String> order, ConstraintValidatorContext constraintValidatorContext) {
        return order.map(UserResponseDTOStreamSorter::isValid).orElse(true);
    }
}
