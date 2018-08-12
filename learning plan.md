## 近期学习计划-8.9
#### 1.Netty权威指南-共25章，计划每章2天。共50天，中间穿插基础知识积累。

#### 第五章:分隔符和定长解码器的应用：
##### day01-8.9:
 - 5.1 DelimiterBasedFrameDecoder
   - TCP粘包/拆包：TCP是个“流”协议，所谓流，就是没有界限的一串数据。
**TCP底层并不了解上层业务数据的具体含义**，它会根据TCP缓冲区的实际情况进行包的划分，所以在业务上认为，
一个完整的包可能会被TCP拆分成多个包进行发送，也有可能把多个小的包封装成一个大的数据报发送,这就是所谓的TCP粘包和拆包问题。
---
code : netty-server/echo-demo
---
- 5.2 FixedLengthFrameDecoder 
   - FixedLengthFrameDecoder 是固定长度解码器，它能够按照指定的长度对消息进行自动解码，开发者不需要考虑TCP的粘包/拆包问题；
   - 利用FixedLengthFrameDecoder解码器，无论一次接受到多少数据报，它都会按照构造函数中设置的固定长度进行解码，
   如果是半包消息并等待下个包到达后进行拼包，直到读取到一个完整的包；
   - 测试，利用telnet；
      - 先启动一个telnet程序连接到TCP服务器中
      - 在连接上的TCP服务器的telnet程序窗口中同时按下 "Ctrl" 和 "]" ,接着在提示窗口中执行
        set localecho命令,按下回车,命令执行,再次按下回车,退出当前窗口 ,会来到一个新的
        telnet窗口,在此窗口中输入的内容就可以显示出来了
---
code : netty-server/echo-demo
--- 
#### 第六章：编解码技术
##### day02-8.10：
 - java序列化的目的主要有两个
    - 网络传输
    - 对象持久化
 - 6.1 java序列化的缺点
    - 6.1.1 无法跨语言
    - 6.1.2 序列化后的码流太大
    - 6.1.3 序列化性能太低
 - 6.2 业界主流的编解码框架
    - Google 的 Protobuf
    - Facebook 的Thrift
    - JBoss Marshalling

 ---
#### 第七章：MessagePack编解码
##### day03-8.11:
- 7.1 MessagePack 介绍
  - MessagePack是一个高效的**二进制**序列化框架，它像JSON一样支持不同语言间的数据交换，但是它的性能更快，序列化之后的码流也更小。
  - 编码高效，性能高；
  - 序列化之后的码流小；
  - 支持跨语言；
  - api使用：
 ---
 code ： messagepack/MsgAPIDemo
 ---
 ##### day04-8.12:
- 7.2 MessagePack 编码器和解码器开发
  - code ： messagepack/
- 7.3 功能测试、粘包、半包支持
   - 遗留：ctx.write();和ctx.writeAndFlush()区别
   
---   
    code : messagepack
---
##### 明日：线程池、各种加密算法 
   