package com.hao.logger;


class Test1{
	public static void test() {
		MyLogger.debug("debug message");
		MyLogger.debug("debug message");
		MyLogger.info("info message");
	}
}

public class Test {

	public static void main(String[] args) {
		Test1.test();
	}
}
