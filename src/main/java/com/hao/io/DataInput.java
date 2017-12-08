package com.hao.io;

public interface DataInput {

	public void dataRecv(Object obj, byte[] datas);
	public void dataRecvTimeout(Object obj);
}
