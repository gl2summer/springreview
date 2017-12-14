package com.hao.io;

public interface DataOutput {
	public void dataOutputStart();
	public boolean dateSend(Object obj, byte[] datas);
	public boolean setTimeout(Integer timeout);
}
