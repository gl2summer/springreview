package com.hao.io;

public interface DataInputCb {

	public void dataRecv(Object obj, byte[] datas);
	public void dataRecvTimeout(Object obj);
}
