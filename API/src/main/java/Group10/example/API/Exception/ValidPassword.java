package Group10.example.API.Exception;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
//logic impliment file
@Constraint(validatedBy = PasswordConstraintValidator.class)
//define where to apply
@Target( { ElementType.METHOD, ElementType.FIELD } )
//time of validate
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}