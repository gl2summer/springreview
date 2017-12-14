package com.hao.io;

public class SockectServer implements DataOutput {

	private DataInputCb dataInput = null;

	public boolean dateSend(Object obj, byte[] datas) {
		return false;
	}

	public boolean setTimeout(Integer timeout) {
		return false;
	}

	public SockectServer(DataInputCb dataInput) {
		super();
		this.dataInput = dataInput;
	}

	public void dataOutputStart() {
		
	}

}
