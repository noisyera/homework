package homework03.nettyClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class NettyClient01 {
    public static void main(String[] args) throws InterruptedException {
        // 启动器
        new Bootstrap()
                // 添加 EventLoop
                .group(new NioEventLoopGroup())
                // 选择客户端 channel 实现
                .channel(NioSocketChannel.class)
                // 添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override   // 连接建立后调用
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                    }
                })
                // 连接到服务器
                .connect(new InetSocketAddress("localhost", 8808))
                // 阻塞方法，直到链接建立后执行
                .sync()
                // 和服务端的连接对象
                .channel()
                .writeAndFlush("hello nio");
    }
}
