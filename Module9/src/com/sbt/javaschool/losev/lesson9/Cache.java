package com.sbt.javaschool.losev.lesson9;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {

    CacheType cacheType();

    String fileNamePrefix() default "";

    boolean zip() default false;

    Class[] identityBy() default {String.class, Integer.class};

    int listList() default 100;
}
