package com.automationteststore.annotations;

import com.automationteststore.enums.CategoryType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface FrameworkAnnotation {

    public String[] author();

    public CategoryType[] category();

}
