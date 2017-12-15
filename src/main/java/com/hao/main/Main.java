package com.hao.main;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.hao.logger.MyLogger;
import com.hao.model.MySystem1Manager;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		//MySystem1Manager mySystem1Manager = context.getBean("mySystem1Manager", MySystem1Manager.class);
	}
}
