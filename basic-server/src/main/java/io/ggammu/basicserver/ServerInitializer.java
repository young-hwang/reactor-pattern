package io.ggammu.basicserver;

public class ServerInitializer {

    public static void main(String[] args) {
        int port = 15000;
        System.out.println("Server ON : " + port);
        Reactor reactor = new Reactor(port);
        reactor.registerHandler(new StreamSayHelloEventHandler());
        reactor.registerHandler(new StreamUpdateProfileEventHandler());
        reactor.startServer();
    }

}
