package reactor.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * ━━━━━━如来保佑━━━━━━
 * ━━━━━━如来保佑━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━永无BUG━━━━━━
 *
 * @author Ledev4
 * @date 2018/6/20 19:26 星期三
 */


public class Client {
    public static void main(String[] args) throws UnknownHostException {

        // 初始化待连接服务端的地址
        String hostName = InetAddress.getLocalHost().toString();
//        int port = 12345;
        int port = 8080;
        try
        {
            Socket client = new Socket(InetAddress.getLocalHost(), port);
            System.out.println("Connected to " + InetAddress.getLocalHost().toString());

            // 分别创建客户端端输入输出流和控制台输入流
            PrintWriter out = new PrintWriter(client.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String input;

            while ((input = stdIn.readLine()) != null)
            {
                //打印来自服务端的反馈数据
                out.println(input);
                out.flush();
                if (input.equals("exit"))
                {
                    break;
                }
                // 打印控制台输入，即客户端向服务端发送的数据
                System.out.println("server: " + in.readLine());
            }
            client.close();
            System.out.println("client stop.");
        }
        catch (UnknownHostException e)
        {
            System.err.println("Don't know about host: " + hostName);
        }
        catch (IOException e)
        {
            System.err.println("Couldn't get I/O for the socket connection");
        }

    }
}
