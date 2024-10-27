package geek.im.server.common.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * @author : HK意境
 * @ClassName : DefaultChannelHandlerInitializer
 * @date : 2024/4/27 0:34
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
public class DefaultChannelHandlerInitializer extends ChannelInitializer<SocketChannel> {

    // 维护与客户端的通道
    private SocketChannel ch;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        this.ch = ch;
        ChannelPipeline pipeline = ch.pipeline();
        // 用来判断是否是 读空闲时间过长，或者写空闲时间过长,30分钟 内没有收到 channel 的数据，就会触发一个事件
        pipeline.addLast(new IdleStateHandler(30, 0, 0, TimeUnit.MINUTES));
        pipeline.addLast("http-codec", new HttpServerCodec());
        // 以块的形式写入
        pipeline.addLast("chunked-write", new ChunkedWriteHandler());
        // 对 HttpMessage 进行聚合处理，
        pipeline.addLast("aggregator", new HttpObjectAggregator(1024 * 64));
    }


}
