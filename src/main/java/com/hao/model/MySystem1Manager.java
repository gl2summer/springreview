package com.hao.model;

import java.util.List;


import com.hao.controller.ProtocolParse;
import com.hao.io.DataInputCb;
import com.hao.io.DataOutput;
import com.hao.storage.Operations;
import com.hao.storage.UserInfo;

public class MySystem1Manager implements DataInputCb {

	private final int COMM_TIMEOUT = 1000;
	private DataOutput dataOutput = null;
	private ProtocolParse protocolParse = null;
	private Operations operations = null;
	private byte[] dataBuff = new byte[1024];
	private int dataBuffLen = 0;

	public ProtocolParse getProtocolParse() {
		return protocolParse;
	}

	public void setProtocolParse(ProtocolParse protocolParse) {
		this.protocolParse = protocolParse;
	}

	public DataOutput getDataOutput() {
		return dataOutput;
	}

	public void setDataOutput(DataOutput dataOutput) {
		this.dataOutput = dataOutput;
	}

	public Operations getOperations() {
		return operations;
	}

	public void setOperations(Operations operations) {
		this.operations = operations;
	}

	private long tickPrev = System.currentTimeMillis();
	
	public void dataRecv(Object obj, byte[] datas) {
		
		//通信超时，则清除缓存
		long tickCurr = System.currentTimeMillis();
		if(tickCurr - tickPrev > COMM_TIMEOUT) {
			dataBuffLen = 0;
		}
		tickPrev = tickCurr;
		
		//追加当前接收的数据到缓存中
		System.arraycopy(datas, 0, dataBuff, dataBuffLen, datas.length);
		dataBuffLen += datas.length;
		System.out.println(datas[0] +" " +dataBuffLen);

		//解析
		if (protocolParse.protCheck(dataBuff, dataBuffLen)) {
			if (protocolParse.protParse(dataBuff, dataBuffLen)) {
				int cmd = protocolParse.getPktCmd();
				System.out.printf("cmd: %c\r\n", cmd);
				switch (cmd) {
				case '0':// list user
					List<UserInfo> list = operations.list();
					dataOutput.dateSend(obj, new byte[] { 'O','K','\r','\n' });
					for (UserInfo u : list) {
						System.out.println(u);
					}
					break;

				default:
					dataOutput.dateSend(obj, new byte[] { 'E','R','R','O','R','\r','\n' });
					break;
				}
				dataBuffLen = 0;
			}
		}
		if (dataBuffLen >= protocolParse.protMaxLength()) {
			dataBuffLen = 0;
		}
	}

	public void dataRecvTimeout(Object obj) {

	}

	public void start() {
		dataOutput.dataOutputStart();
	}
}
