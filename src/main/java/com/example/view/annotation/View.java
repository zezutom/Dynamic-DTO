package com.example.view.annotation;

import java.lang.annotation.*;

/**
 * Created by tom on 31/01/2016.
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Views.class)
public @interface View {
    String value() default "";
}
