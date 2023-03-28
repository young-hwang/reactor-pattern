package io.ggammu.basicserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Demultiplex implements Runnable {

    private final int HEADER_SIZE = 6;

    private Socket socket;
    private HandleMap handleMap;

    public Demultiplex(Socket socket, HandleMap handleMap) {
        this.socket = socket;
        this.handleMap = handleMap;
    }

    @Override
    public void run() {
        InputStreamReader inputStreamReader = null;
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[HEADER_SIZE];
            inputStream.read(buffer);
            String header = new String(buffer);
            handleMap.get(header).handleEvent(inputStream);
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
