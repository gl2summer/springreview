package com.hao.io;

public class Test implements DataInputCb {

	private NettyServer nettyServer = null;
	private Object object = null;

	public Test() {
		nettyServer = new NettyServer();
		nettyServer.setDataInputCb(this);
		nettyServer.start(12345);
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
		byte[] datas = new byte[] { 0x31, 0x32 };
		nettyServer.dateSend(object, datas);
	}

	public static void main(String[] args) throws Exception {
		Test test = new Test();
	}

}
