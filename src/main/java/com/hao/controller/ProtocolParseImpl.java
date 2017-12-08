package com.hao.controller;

public class ProtocolParseImpl implements ProtocolParse {

	private static final byte BEGIN = '@';
	private static final byte END = '#';

	private Byte pktBegin;
	private Byte pktEnd;
	private Byte pktCmd;
	private byte[] pktData;

	public boolean protParse(byte[] oriData) {
		if ((oriData.length == 3) && (oriData[0] == BEGIN) && (oriData[1] >= '0') && (oriData[1] <= '9')
				&& (oriData[2] == END)) {
			pktBegin = oriData[0];
			pktCmd = oriData[1];
			pktEnd = oriData[2];
			pktData = oriData;
			return true;
		}
		return false;
	}

	public boolean protCombine(byte cmd) {
		if ((cmd >= '0') && (cmd <= '9')) {
			pktData = new byte[] { BEGIN, cmd, END };
			return true;
		}
		return false;
	}

	public Byte getPktBegin() {
		return pktBegin;
	}

	public Byte getPktEnd() {
		return pktEnd;
	}

	public Byte getPktCmd() {
		return pktCmd;
	}

	public byte[] getPktData() {
		return pktData;
	}

}
