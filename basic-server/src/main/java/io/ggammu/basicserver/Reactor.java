package io.ggammu.basicserver;

import java.io.IOException;
import java.net.ServerSocket;

public class Reactor {

    private ServerSocket serverSocket;
    private HandleMap handleMap;

    public Reactor(int port) {
        this.handleMap = new HandleMap();
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        Dispatcher dispatcher = new Dispatcher();

        while (true){
            dispatcher.dispatch(this.serverSocket, this.handleMap);
        }
    }

    public void registerHandler(EventHandler eventHandler) {
        handleMap.put(eventHandler.getHandler(), eventHandler);
    }

    public void removeHandler(EventHandler eventHandler) {
        handleMap.remove(eventHandler.getHandler());
    }

}
