package com.hao.io;

public interface DataOutput {
	public boolean dateSend(Object obj, byte[] datas);
	public boolean setTimeout(Integer timeout);
}
