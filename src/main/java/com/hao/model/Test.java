package com.hao.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		MySystem1Manager mySystem1Manager = context.getBean("mySystem1Manager", MySystem1Manager.class);
	}

}
