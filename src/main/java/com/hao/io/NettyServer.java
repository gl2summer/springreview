package com.hao.io;

import java.net.InetSocketAddress;

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

public class NettyServer implements DataOutput {

	private DataInput dataInput = null;

	public void start(int port) throws InterruptedException {
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
			group.shutdownGracefully().sync();
		}
	}

	public DataInput getDataInput() {
		return dataInput;
	}

	public void setDataInput(DataInput dataInput) {
		this.dataInput = dataInput;
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

	class ServerInHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			ByteBuf byteBuf = (ByteBuf) msg;
			byte[] datas = new byte[byteBuf.readableBytes()];
			byteBuf.readBytes(datas);
			if (dataInput != null) {
				dataInput.dataRecv(ctx, datas);
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
			//ctx.close();
		}

	}

}
