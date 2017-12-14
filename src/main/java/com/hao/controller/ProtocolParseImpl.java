package com.hao.controller;



public class ProtocolParseImpl implements ProtocolParse {
	
	private static final byte BEGIN = '@';
	private static final byte END = '#';
	private static final int PROT_LEN = 3;

	private Byte pktBegin;
	private Byte pktEnd;
	private Byte pktCmd;
	private byte[] pktData;

	public static int search(byte[] data, int fromIndex, int toIndex, byte key) {
		int targetIndex = -1;
		
		if(toIndex < fromIndex)
			return targetIndex;
		if(data.length <= 0)
			return targetIndex;
		if(data.length < fromIndex+1)
			return targetIndex;
		
		for(int i=fromIndex; i<toIndex; i++) {
			if(i >= data.length)
				break;
			if(data[i] == key) {
				targetIndex = i;
				break;
			}
		}
		
		return targetIndex;
	}
	
	public boolean protCheck(byte[] data, int dataRealLen) {
		if((data.length <= 0)||(data.length < dataRealLen))
			return false;
		
		int beginPos = search(data, 0, dataRealLen-1, BEGIN);
		if(beginPos < 0)
			return false;
		
		int endPos = search(data, beginPos+1, dataRealLen, END);
		if(endPos < 0)
			return false;
		
		if(endPos - beginPos != 2)
			return false;
		
		if((data[beginPos+1] < '0') || (data[beginPos+1]) > '9')
			return false;
		
		return true;
	}
	
	public boolean protParse(byte[] oriData, int dataRealLen) {
		
		if((oriData.length <= 0)||(oriData.length < dataRealLen))
			return false;
		
		if ((dataRealLen >= 3) && (oriData[0] == BEGIN) && (oriData[1] >= '0') && (oriData[1] <= '9')
				&& (oriData[2] == END)) {
			pktBegin = oriData[0];
			pktCmd = oriData[1];
			pktEnd = oriData[2];
			pktData = new byte[] { pktBegin, pktCmd, pktEnd };
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

	public int protMaxLength() {
		return PROT_LEN;
	}
}
