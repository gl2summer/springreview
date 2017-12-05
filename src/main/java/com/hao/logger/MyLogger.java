package com.hao.logger;

import org.apache.log4j.Logger;

public class MyLogger {

	private static Logger logger = Logger.getLogger(MyLogger.class);

	public static void debug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	public static void debug(Object message) {
		logger.debug(message);
	}

	public static void error(Object message) {
		logger.error(message);
	}

	public static void error(Object message, Throwable t) {
		logger.error(message, t);
	}

	public static void fatal(Object message) {
		logger.fatal(message);
	}

	public static void fatal(Object message, Throwable t) {
		logger.fatal(message, t);
	}

	public static void info(Object message) {
		logger.info(message);
	}

	public static void info(Object message, Throwable t) {
		logger.info(message, t);
	}

	public static void warn(Object message) {
		logger.warn(message);
	}

	public static void warn(Object message, Throwable t) {
		logger.warn(message, t);
	}

	public static Logger getLogger() {
		return logger;
	}
}
