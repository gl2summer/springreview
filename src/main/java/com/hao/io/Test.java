package com.hao.io;

public class Test implements DataInput, Runnable {

	private Object object = null;
	private NettyServer nettyServer = null;

	{
		nettyServer = new NettyServer();
	}

	public Object getObject() {
		return object;
	}

	public void dataRecv(Object obj, byte[] datas) {
		this.object = obj;
		for (byte b : datas) {
			System.out.printf("%02x", b);
		}
		send();
	}

	public void dataRecvTimeout(Object obj) {
	}

	public void send() {
		Object object = this.getObject();
		byte[] datas = new byte[] { 0x31, 0x32};
		nettyServer.dateSend(object, datas);
	}

	public void run() {
		nettyServer.setDataInput(this);
		try {
			nettyServer.start(12345);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Test test = new Test();
		Thread thread = new Thread(test);
		thread.start();
		thread.sleep(5000);
		test.send();
	}

}
