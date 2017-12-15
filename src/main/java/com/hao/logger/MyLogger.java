package com.hao.logger;

import org.apache.log4j.Logger;

public class MyLogger {

	public static void debug(Object message, Throwable t) {
		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String className = stackTraceElements[2].getClassName();
			Class<?> clazz = Class.forName(className);
			Logger logger = Logger.getLogger(clazz);
			logger.debug(message, t);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void debug(Object message) {
		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String className = stackTraceElements[2].getClassName();
			Class<?> clazz = Class.forName(className);
			Logger logger = Logger.getLogger(clazz);
			logger.debug(message);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void error(Object message) {

		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String className = stackTraceElements[2].getClassName();
			Class<?> clazz = Class.forName(className);
			Logger logger = Logger.getLogger(clazz);
			logger.error(message);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void error(Object message, Throwable t) {

		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String className = stackTraceElements[2].getClassName();
			Class<?> clazz = Class.forName(className);
			Logger logger = Logger.getLogger(clazz);
			logger.error(message, t);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fatal(Object message) {

		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String className = stackTraceElements[2].getClassName();
			Class<?> clazz = Class.forName(className);
			Logger logger = Logger.getLogger(clazz);
			logger.fatal(message);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fatal(Object message, Throwable t) {

		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String className = stackTraceElements[2].getClassName();
			Class<?> clazz = Class.forName(className);
			Logger logger = Logger.getLogger(clazz);
			logger.fatal(message, t);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void info(Object message) {

		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String className = stackTraceElements[2].getClassName();
			Class<?> clazz = Class.forName(className);
			Logger logger = Logger.getLogger(clazz);
			logger.info(message);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void info(Object message, Throwable t) {

		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String className = stackTraceElements[2].getClassName();
			Class<?> clazz = Class.forName(className);
			Logger logger = Logger.getLogger(clazz);
			logger.info(message, t);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void warn(Object message) {

		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String className = stackTraceElements[2].getClassName();
			Class<?> clazz = Class.forName(className);
			Logger logger = Logger.getLogger(clazz);
			logger.warn(message);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void warn(Object message, Throwable t) {

		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String className = stackTraceElements[2].getClassName();
			Class<?> clazz = Class.forName(className);
			Logger logger = Logger.getLogger(clazz);
			logger.warn(message, t);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static Logger getLogger(Class clazz) {
		return Logger.getLogger(clazz);
	}
}
