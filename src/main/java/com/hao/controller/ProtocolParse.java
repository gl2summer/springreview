package com.hao.controller;

public interface ProtocolParse {

	public boolean protParse(byte[] data);
	public boolean protCombine(byte cmd);
	
	public Byte getPktBegin();
	public Byte getPktEnd();
	public Byte getPktCmd();
	public byte[] getPktData();
}
