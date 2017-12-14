package com.hao.test;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.netty.channel.DefaultAddressedEnvelope;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE, METHOD })
public @interface MyAnnotation {

	String desc();
	String autor();
	int age() default 18; 
}
