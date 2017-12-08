package com.hao.io;

public class SockectServer implements DataOutput {

	private DataInput dataInput = null;

	public boolean dateSend(Object obj, byte[] datas) {
		return false;
	}

	public boolean setTimeout(Integer timeout) {
		return false;
	}

	public SockectServer(DataInput dataInput) {
		super();
		this.dataInput = dataInput;
	}

}
