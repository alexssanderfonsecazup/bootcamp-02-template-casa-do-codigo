package br.com.bootcamp.casaDoCodigo.compartilhado.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ExistsValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface Exists {

    String message() default "{br.com.bootcamp.casaDoCodigo.exists}";

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default{};

    String fieldName();


    Class<?>  domainClass();
}
