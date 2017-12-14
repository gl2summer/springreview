package com.hao.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@MyAnnotation(desc = "class", autor = "cjh")
public class TestAnnotation {

	@MyAnnotation(desc = "method", autor = "cjh", age = 19)
	public void func() {

	}

	public static void main(String[] args) {
		try {
			Class<?> cls = Class.forName("com.hao.test.TestAnnotation");
			boolean isAnnotationExist = cls.isAnnotationPresent(MyAnnotation.class);

			if (isAnnotationExist) {
				MyAnnotation ma1 = cls.getAnnotation(MyAnnotation.class);
				System.out.println(ma1.desc() + " " + ma1.autor() + " " + ma1.age());
			}

			Annotation[] as1 = cls.getAnnotations();
			for (Annotation a : as1) {
				if (a instanceof MyAnnotation) {
					MyAnnotation ma = (MyAnnotation) a;
					System.out.println(ma.desc() + " " + ma.autor() + " " + ma.age());
				}
			}

			Method[] ms = cls.getMethods();
			for (Method m : ms) {
				Annotation[] as2 = m.getAnnotations();
				for (Annotation a : as2) {

					if (a instanceof MyAnnotation) {
						MyAnnotation ma = (MyAnnotation) a;
						System.out.println(ma.desc() + " " + ma.autor() + " " + ma.age());
					}
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
