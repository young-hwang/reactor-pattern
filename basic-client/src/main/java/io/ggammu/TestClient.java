package io.ggammu;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {

    public static void main(String[] args) {
        System.out.println("Client ON");

        try {
            String message;

            Socket socket = new Socket("127.0.0.1", 15000);
            OutputStream outputStream = socket.getOutputStream();
            message = "0x5001|홍길동|22";
            outputStream.write(message.getBytes());
            socket.close();

            Socket socket2 = new Socket("127.0.0.1", 15000);
            OutputStream outputStream2 = socket2.getOutputStream();
            message = "0x6001|hong|1234|홍길동|22|남성";
            outputStream2.write(message.getBytes());
            socket.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
