1. 标准 Java 库中有一个Future接口，但对于 Netty 来说并不方便——我们只能向Future询问操作是否完成或阻塞当前线程直到操作完成。 
这就是Netty 有自己的ChannelFuture接口的原因。我们可以将回调传递给ChannelFuture，它将在操作完成时调用。
2. Netty 使用事件驱动的应用程序范例，因此数据处理的管道是通过处理程序的事件链。事件和处理程序可以与入站和出站数据流相关。入站事件可以是以下几种：

通道激活和停用
读取操作事件
异常事件
用户事件
出站事件更简单，通常与打开/关闭连接和写入/刷新数据有关。

Netty 应用程序由几个网络和应用程序逻辑事件及其处理程序组成。通道事件处理程序的基本接口是ChannelHandler及其继承者ChannelOutboundHandler
和ChannelInboundHandler。

3. Netty 提供了巨大的 ChannelHandler 实现层次结构。值得注意的是那些只是空实现的适配器，例如ChannelInboundHandlerAdapter
和ChannelOutboundHandlerAdapter。当我们只需要处理所有事件的一个子集时，我们可以扩展这些适配器。
此外，还有许多特定协议的实现，例如 HTTP，例如HttpRequestDecoder、HttpResponseEncoder、HttpObjectAggregator。在 Netty 的 Javadoc 
中熟悉它们会很好。
4. 编码器和解码器
当我们使用网络协议时，我们需要执行数据序列化和反序列化。为此，Netty为能够解码传入数据的解码器引入了ChannelInboundHandler的特殊扩展。 
大多数解码器的基类是ByteToMessageDecoder。
为了对传出数据进行编码，Netty 对ChannelOutboundHandler进行了扩展，称为编码器。MessageToByteEncoder是大多数编码器实现的基础。
我们可以使用编码器和解码器将消息从字节序列转换为 Java 对象，反之亦然。
5. 让我们创建一个代表一个简单协议服务器的项目，它接收请求、执行计算并发送响应。
```xml
<dependency>
    <groupId>io.netty</groupId>
    <artifactId>netty-all</artifactId>
    <version>4.1.52.Final</version>
</dependency>
```
6. 数据模型
   RequestData 和 ResponseData
7. 请求解码器
现在我们需要为我们的协议消息创建编码器和解码器。

需要注意的是，Netty 与套接字接收缓冲区一起工作，它不是表示为队列，而是表示为一堆字节。这意味着当服务器未收到完整消息时，可以调用我们的入站处理程序。

我们必须确保在处理之前我们已经收到了完整的消息，并且有很多方法可以做到这一点。

首先，我们可以创建一个临时的ByteBuf并将所有入站字节附加到它，直到我们获得所需的字节数：