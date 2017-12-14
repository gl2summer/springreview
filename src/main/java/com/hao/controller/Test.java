package com.hao.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
//		byte[] data = new byte[] { '@', '1', '#' };
//		ProtocolParseImpl parseImpl = new ProtocolParseImpl();
//		boolean r = parseImpl.protCheck(data, data.length);
//		System.out.println(r);
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		ProtocolParse parse = ctx.getBean("protocolParse", ProtocolParse.class);
		System.out.println("--------------result: " +parse.getPktBegin());
		parse.protCombine((byte)'A');
	}

}
