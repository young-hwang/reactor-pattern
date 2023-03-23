package io.ggammu.basicserver;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

public class StreamUpdateProfileEventHandler implements EventHandler {

    private static final int DATA_SIZE = 1024;
    private static final int TOKEN_NUM = 5;

    @Override
    public String getHandler() {
        return "0x6001";
    }

    public void handleEvent(InputStream inputStream) {
        try {
            byte[] buffer = new byte[DATA_SIZE];
            inputStream.read(buffer);
            String data = new String(buffer);

            String[] params = new String[TOKEN_NUM];
            StringTokenizer stringTokenizer = new StringTokenizer(data, "|");
            int i = 0;
            while (stringTokenizer.hasMoreTokens()) {
                params[i] = stringTokenizer.nextToken();
                ++i;
            }

            updateProfile(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProfile(String[] params) {
        System.out.println("UpdateProfile -> " +
                " id : " + params[0] +
                " password : " + params[1] +
                " name : " + params[2] +
                " age : " + params[3] +
                " gender : " + params[4]
        );
    }

}
