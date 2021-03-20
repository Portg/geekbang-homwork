package org.geektimes.projects.user.validator.bean.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserMobileValidAnnotationValidator.class)
public @interface UserMoblieValid {

	String message() default "手机号格式错误";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
