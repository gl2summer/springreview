package com.hao.display;


class Object1 extends Object {

	public int counter;
	
	public Object1(int counter) {
		super();
		this.counter = counter;
	}
}

class MyThread1 extends Thread {

	private Object1 lock = null;
	private boolean put = false;

	public MyThread1(Object1 lock, boolean put) {
		this.lock = lock;
		this.put = put;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (lock) {
				if (put == false) {// get
					while (lock.counter <= 0) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("get");
					lock.counter--;
					lock.notify();
//					try {
//						sleep(200);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				}
				else {
					while (lock.counter > 0) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("put");
					lock.counter++;
					lock.notify();
//					try {
//						sleep(200);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				}
			}
		}
	}
}

public class Test {

	private static Object1 counter = new Object1(0);

	public static void main(String[] args) {
		Thread t1 = new Thread(new MyThread1(counter, true));
		Thread t2 = new Thread(new MyThread1(counter, false));
		t1.start();
		t2.start();
	}
}
