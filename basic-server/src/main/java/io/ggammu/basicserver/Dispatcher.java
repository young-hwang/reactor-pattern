package io.ggammu.basicserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Dispatcher {

    private final int HEADER_SIZE = 6;

    public void dispatch(ServerSocket serverSocket, HandleMap handleMap){
        try {
            Socket socket = serverSocket.accept();
            Demultiplex demultiplex = new Demultiplex(socket, handleMap);
            Thread thread = new Thread(demultiplex);
            thread.start();
//            demultiplex(socket, handleMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void demultiplex(Socket socket, HandleMap handleMap) {
//        InputStreamReader inputStreamReader = null;
//        try {
//            InputStream inputStream = socket.getInputStream();
//            byte[] buffer = new byte[HEADER_SIZE];
//            inputStream.read(buffer);
//            String header = new String(buffer);
//            handleMap.get(header).handleEvent(inputStream);
//            socket.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
