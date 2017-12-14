package com.hao.controller;



public interface ProtocolParse {

	public boolean protCheck(byte[] data, int dataRealLen);
	public boolean protParse(byte[] data, int dataRealLen);
	public boolean protCombine(byte cmd);
	public int protMaxLength();
	
	public Byte getPktBegin();
	public Byte getPktEnd();
	public Byte getPktCmd();
	public byte[] getPktData();
}
