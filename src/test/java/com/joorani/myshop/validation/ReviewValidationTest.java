package com.joorani.myshop.validation;

import com.joorani.myshop.controller.dtos.ReviewSaveRequestDto;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewValidationTest {

    @Test
    void blankContent() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        ReviewSaveRequestDto requestDto = new ReviewSaveRequestDto("");

        Set<ConstraintViolation<ReviewSaveRequestDto>> violations = validator.validate(requestDto);
        assertThat(violations).isNotEmpty();
        violations.forEach(error -> {
            System.out.println(error.getMessage());
            assertThat(error.getMessage()).isEqualTo("공백은 입력할 수 없습니다.");
        });

    }
}
