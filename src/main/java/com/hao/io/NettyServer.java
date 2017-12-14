package com.hao.io;

import java.net.InetSocketAddress;
import java.util.Arrays;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer extends Thread implements DataInput, DataOutput {

	private static final int dataRecvBytesMin = 32;
	private static final int dataRecvBytesMax = 2048;

	private int port = 0;
	private DataInputCb dataInputCb = null;// 当接收到数据时，回调
	private byte[] dataRecvedBuff = null;// 当回调接口为null时，数据将缓存
	private int dataRecvedLength = 0;// 当前缓存的数据长度

	@Override
	public void run() {
		ServerBootstrap b = new ServerBootstrap();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			b.group(group);
			b.channel(NioServerSocketChannel.class);// 设置nio类型的channel
			b.localAddress(new InetSocketAddress(port));// 设置监听端口
			b.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ServerInHandler());
				}
			});
			// b.option(ChannelOption.SO_BACKLOG, 128);
			// b.childOption(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture f = b.bind().sync();

			f.channel().closeFuture().sync();// 一直等待，直到channel关闭
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				group.shutdownGracefully().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start(int port) {
		this.port = port;
		start();
	}

	public void setDataInputCb(DataInputCb dataInputCb) {
		this.dataInputCb = dataInputCb;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean dateSend(Object obj, byte[] datas) {
		ChannelHandlerContext ctx = (ChannelHandlerContext) obj;

		ByteBuf buf = ctx.alloc().buffer(datas.length);
		buf.writeBytes(datas);
		ctx.write(buf);
		ctx.flush();
		return true;
	}

	public boolean setTimeout(Integer timeout) {
		return false;
	}
	
	public void dataOutputStart() {
		start(port);
	}

	class ServerInHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			ByteBuf byteBuf = (ByteBuf) msg;
			int datasLen = byteBuf.readableBytes();
			byte[] datas = new byte[datasLen];
			byteBuf.readBytes(datas);

			synchronized (this) {
				// 若接收数据回调存在，则回调，否则数据将被缓存
				if (dataInputCb != null) {
					dataInputCb.dataRecv(ctx, datas);
				} else {
					if (dataRecvedBuff == null) {
						dataRecvedBuff = new byte[datasLen + dataRecvBytesMin];// 申请初始数组
						dataRecvedLength = 0;// 初始化缓存长度
					}
					int restLen = dataRecvedBuff.length - dataRecvedLength;
					if (restLen < datasLen) {
						int newLength = dataRecvedLength + datasLen + dataRecvBytesMin;
						Arrays.copyOf(dataRecvedBuff, newLength);// 扩大缓存
					}

					System.arraycopy(datas, 0, dataRecvedBuff, dataRecvedLength, datasLen);
					dataRecvedLength += datasLen;
				}
			}

			byteBuf.release();
		}

		@Override
		public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
			ctx.flush();
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			cause.printStackTrace();
			// ctx.close();
		}

	}

	public boolean takeData(Object obj, byte[] buff) {

		synchronized (this) {
			if (dataRecvedBuff == null) {
				return false;
			}
			System.arraycopy(dataRecvedBuff, 0, buff, 0, dataRecvedLength);
			dataRecvedLength = 0;
			return true;
		}
	}

}
