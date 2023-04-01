package io.ggammu.echoserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerInitializer {

    private static int PORT = 45000;
    private static int THREADPOOLSIZE = 8;
    private static int INITIALSIZE = 4;
    private static int BACKLOG = 50;

    public static void main(String[] args) {
        System.out.println("SERVER START!!");

        // 고정 쓰레드 풀 생성
        ExecutorService service = Executors.newFixedThreadPool(THREADPOOLSIZE);

        //
        try {
            AsynchronousChannelGroup asynchronousChannelGroup = AsynchronousChannelGroup.withCachedThreadPool(service, INITIALSIZE);
            AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open(asynchronousChannelGroup);
            listener.bind(new InetSocketAddress(PORT), BACKLOG);
            listener.accept(listener, new Dispatcher());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
