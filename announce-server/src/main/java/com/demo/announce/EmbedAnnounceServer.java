package com.demo.announce;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.internal.SystemPropertyUtil;

import java.util.concurrent.atomic.AtomicInteger;

import static com.demo.announce.AnnounceConstants.SERVER_STATUS_START;
import static com.demo.announce.AnnounceConstants.SERVER_STATUS_STOP;

public class EmbedAnnounceServer implements IServer {

    private final int port;
    private static final int DEFAULT_EVENT_LOOP_THREADS = Math.max(1,
                    SystemPropertyUtil.getInt("io.netty.eventLoopThreads", Runtime.getRuntime().availableProcessors() * 2));
    private final AtomicInteger currentState = new AtomicInteger(SERVER_STATUS_STOP);

    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;

    public EmbedAnnounceServer(int port) {
        this.port = port;
    }

    @Override
    public void start() throws Exception {
        if (!currentState.compareAndSet(SERVER_STATUS_STOP, SERVER_STATUS_START)) {
            return;
        }


        ServerBootstrap b = new ServerBootstrap();
        this.bossGroup = new NioEventLoopGroup(1);
        this.workerGroup = new NioEventLoopGroup(DEFAULT_EVENT_LOOP_THREADS);
        b.group(bossGroup,workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 128)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline p = ch.pipeline();
                                p.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
                                //todo implement...
                            }
                        })
                        .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                        .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                        .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                        .childOption(ChannelOption.SO_TIMEOUT, 10)
                        .childOption(ChannelOption.TCP_NODELAY, true)
                        .childOption(ChannelOption.SO_RCVBUF, 32 * 1024);

    }

    @Override
    public void stop() throws Exception {

    }
}
